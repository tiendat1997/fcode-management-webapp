
// ************* END MEMBER ROW ***************** // 


// ******************* END MEMBER TABLE ***************** //

import React from 'react'; 
import ReactDOM from 'react-dom';

import MemberTable from './memberTable.jsx' ;
// optionExpired: 3 - both, 1 - current, 2 - expired

class MemberPanel extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			page: null,
			defaultSize: 5,
			numPage: 1,
			filterValue: null,
			openFilter: false,
			optionExpired: 3
		};

		this.handlePagination = this.handlePagination.bind(this);
		this.loadMembersFromServer = this.loadMembersFromServer.bind(this);
	}


	componentDidMount(){
 		this.loadMembersFromServer(0, this.state.defaultSize, 3);
 	}

    filterMembersFromServer(filterValue, page, size, option){
    	//alert(option);
    	this.setState({
    		filterValue: filterValue
    	}); 
    	var expired = this.state.optionExpired; 
    	if (option != null) {
    	    expired = option;
    	}

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
				"optionExpired": expired,
				"page": page,
				"size": sizeState
			},
			cached: false
		}); 

		request.done((data) => {						
			this.setState({page: data});
		});

		request.fail((msg) => {
			toastr.error("Error Occurs");
		});
    }




	loadMembersFromServer(page, size, option){
		var expired = this.state.optionExpired;
		if (option != null) {
			expired = option;
		} 
				
		var self = this; 
		var request = $.ajax({
			url: '/admin/api/account/get',
			data: {			
				"optionExpired": expired,
				"page": page,
				"size": size
			},
			cached: false
		}); 

		request.done((data) => {						
			this.setState({page: data});
		});

		request.fail((msg) => {
			toastr.error("Cannot Get Why");
		});


	}


	handlePagination(page){
		
		if (this.state.openFilter && this.state.filterValue != null){			
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
	filterExpiredCurrent(evt){
		
		
		if (this.state.openFilter){
			this.filterMembersFromServer(this.state.filterValue, 0, this.state.defaultSize, evt.target.value);			
		}
		else {
			this.loadMembersFromServer(0 , this.state.defaultSize, evt.target.value);
		}

		this.setState({
			optionExpired: evt.target.value,
			numPage: 1
		});
	}

	changeRowOfPage(evt){		
		
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
			for(var i = 0; i < length; i++){				
				pages.push(					
					<li className="page-item" key={'li' + i}>
						<a className="page-link" onClick={this.handlePagination.bind(this,i)}>{i + 1}</a>
					</li>					
				);
			}	
			

			return (
				<div className="filterable"
					ref={(panel) => { this.panel = panel; }}
				>						
							<div className="row">
								<div className="col-sm-6">
									<div className="row">
										<div className="col-sm-12">
											<h3 className="panel-title">Panel Heading</h3>								
										</div>								
									</div>																		 
								</div>
								<div className="col-sm-6 text-right">	
									<select className="form-control" onChange={this.filterExpiredCurrent.bind(this)}>
										<option value="3">both</option>
										<option value="1">current</option>
										<option value="2">expired</option>										
									</select>								
								 	<select className="form-control" onChange={this.changeRowOfPage.bind(this)}>				    							
				    							<option value="5">5</option>
				    							<option value="10">10</option>
				    							<option value="20">20</option>
				    							<option value="50">50</option>
				    							<option value="100">100</option>
				    				</select>
									<button 
										className="btn btn-sm btn-primary btn-circle"
										onClick={this.clickToFilter.bind(this, this)}>
										<i className="fa fa-filter" aria-hidden="true"></i>
                    				</button>
									<button 
										data-toggle="modal" 
										data-target="#addNewModal"
										className="btn btn-sm btn-primary btn-circle">											
										<i className="fa fa-plus" aria-hidden="true"></i>
									</button>
								</div>
							</div>
						

						<MemberTable 											
							members = {this.state.page.content}
							filterMembersFromServer = {self.filterMembersFromServer.bind(self)}
						/>
						
						<div className="row">
								<div className="col col-md-4">
									Page {this.state.numPage} of {pages.length}
								</div>
								<div className="col col-md-8">								
									<ul className="pagination pull-right">									
										{pages}
									</ul>									
															
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
