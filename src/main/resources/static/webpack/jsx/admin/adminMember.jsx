
// ************* END MEMBER ROW ***************** // 


// ******************* END MEMBER TABLE ***************** //

import React from 'react'; 
import ReactDOM from 'react-dom';

import MemberTable from './memberTable.jsx' ;


class MemberPanel extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			page: null,
			defaultSize: 5,
			numPage: 1,
			filterValue: null,
			openFilter: false
		};

		this.handlePagination = this.handlePagination.bind(this);
		this.loadMembersFromServer = this.loadMembersFromServer.bind(this);
	}


	componentDidMount(){
 		this.loadMembersFromServer(0, this.state.defaultSize);
 	}

    filterMembersFromServer(filterValue, page, size){
    	this.setState({
    		filterValue: filterValue
    	}); 
    	var sizeState = this.state.defaultSize;
    	if (size != null) {
    		sizeState = size;
    	}
    	var self = this; 
		var request = $.ajax({
			url: '/admin/api/account/filter',
			data: {
				"username": filterValue.username,
				"fullname": filterValue.fullname,
				"email": filterValue.email,
				"phone": filterValue.phone,
				"grade": filterValue.grade,
				"page": page,
				"size": sizeState
			},
			cached: false
		}); 

		request.done((data) => {			
			console.log(data);
			this.setState({page: data});
		});

		request.fail((msg) => {
			toastr.error("Error Occurs");
		});
    }


	loadMembersFromServer(page, size){
		var self = this; 
		var request = $.ajax({
			url: '/admin/api/account/get',
			data: {
				"page": page,
				"size": size
			},
			cached: false
		}); 

		request.done((data) => {			
			console.log(data);
			this.setState({page: data});
		});

		request.fail((msg) => {
			toastr.error("Error Occurs");
		});


	}


	handlePagination(page){
		
		if (this.state.openFilter){
			this.filterMembersFromServer(this.state.filterValue, page);	
		}
		else{
			this.loadMembersFromServer(page, this.state.defaultSize);
		} 

		
		this.setState({
			numPage: page + 1
		});
	}

	clickToFilter(){
		 		 
		 var filters = this.panel.getElementsByClassName('filters');
		 var inputs = this.panel.getElementsByTagName('input');
		 

		 for(var item of inputs){		 	
		 	var disabled = item.getAttribute('disabled');
		 	if (disabled == ''){		 		
		 		item.removeAttribute('disabled');
		 		this.setState({
		 			openFilter: true
		 		});
		 	}		  	
		 	else {
		 		item.setAttribute('disabled', '');
		 		this.setState({
		 			openFilter: true
		 		});
		 	}
		 }
		
		 this.forceUpdate();		 
         
	}

	changeRowOfPage(evt){
		//console.log(evt.target.value);
		
		if (this.state.openFilter){
			this.filterMembersFromServer(this.state.filterValue, 0, evt.target.value);			
		}
		else {
			this.loadMembersFromServer(0 ,evt.target.value);
		}
		
		this.setState({
			defaultSize: evt.target.value,
			numPage: 1			
		});

	}

	render(){

		var pages = []; 
		var self = this; 
	
		if (this.state.page != null) {
			
			
			var length = this.state.page.totalPages;			


			// from 0 
			for(var i = 0; i < length-1; i++){				
				pages.push(					
					<li>
						<a onClick={this.handlePagination.bind(this,i)}>{i + 1}</a>
					</li>					
				);
			}	
			

			return (
				<div className="panel panel-default panel-table filterable"
					ref={(panel) => { this.panel = panel; }}
				>
						<div className="panel-heading">
							<div className="row">
								<div className="col-sm-6">
									<div className="row">
										<div className="col-sm-12">
											<h3 className="panel-title">Panel Heading</h3>								
										</div>								
									</div>																		 
								</div>
								<div className="col-sm-6 text-right">
								 	<select className="form-control" onChange={this.changeRowOfPage.bind(this)}>				    							
				    							<option value="5">5</option>
				    							<option value="10">10</option>
				    							<option value="20">20</option>
				    							<option value="50">50</option>
				    							<option value="100">100</option>
				    				</select>
									<button 
										onClick={this.clickToFilter.bind(this, this)}
										className="btn btn-default btn-xs btn-filter">
                    						<span className="glyphicon glyphicon-filter"></span> 
                    							Filter
                    				</button>
									<button type="button"
										className="btn btn-sm btn-primary btn-create">Create
										New</button>
								</div>
							</div>
						</div>

						<MemberTable 							
							members = {this.state.page.content}
							filterMembersFromServer = {self.filterMembersFromServer.bind(self)}
						/>

						<div className="panel-footer">
							<div className="row">
								<div className="col col-xs-4">
									Page {this.state.numPage} of {pages.length}
								</div>
								<div className="col col-xs-8">
									<ul className="pagination hidden-xs pull-right">									
										{pages}
									</ul>									
								</div>
							</div>
						</div>
				</div>

			);
		}
		else {
			return(
				<div></div>	
			);
		}
	
	}

};



ReactDOM.render(
	<MemberPanel />,document.getElementById('member-panel-react')
);
