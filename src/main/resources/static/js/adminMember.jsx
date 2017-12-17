

class Member extends React.Component{
	constructor(props){
		super(props);

	}

	render(){
		var delBtn = [];
		var isExpired = null; 

		if (this.props.member.expired === true){
			delBtn.push(
				<button className="btn btn-default">
                  		<em className="fa fa-trash"></em>
                 </button>
			);

			isExpired = 'expired'; //css
		}
		else {
			delBtn.push(
				<button className="btn btn-danger" onClick={this.props.handleDelete.bind(null, this.props.member)}>
                  <em className="fa fa-trash"></em>
                </button>
			);
		}

		return (
			 <tr className={isExpired}>				                           
                <td className="hidden-xs">{this.props.count}</td>
                <td>{this.props.member.username}</td>
                <td>{this.props.member.fullname}</td>
                <td>{this.props.member.email}</td>
                <td>{this.props.member.phone}</td>
                <td>{this.props.member.grade}</td>
                <td align="center">
                  <button className="btn btn-default" onClick={this.props.handleUpdate.bind(null, this.props.member)}>
                  	<em className="fa fa-pencil"></em>
                  </button>
                  {delBtn}
                </td>
              </tr>				                       	
		);
	}
};



class MemberTable extends React.Component{
	constructor(props){
		super(props); 

		
		// this.handleDelete.bind(this);
		// this.handleUpdate.bind(this);
	}

	handleDelete(member){
		alert("handle delete");
	}

	handleUpdate(member){
		alert("handleUpdate");
	}

	// onkeyup
	onFilterKeyUp(e){
		console.log(this.filterGrade.value);

		
			var filterValue = {
				username: this.filterUsername.value,
				fullname: this.filterFullname.value,
				email: this.filterEmail.value,
				phone: this.filterPhone.value,
				grade: this.filterGrade.value
			}	
			// grade: this.filterGrade.value
			
			this.props.filterMembersFromServer(filterValue, 0);			
		
	}

	render(){
		var rows = []; 
		if (this.props.members != null) {
			this.props.members.forEach((member, index) => {
			rows.push(
				<Member
					count={index}
					member={member}
					handleDelete={this.handleDelete}
					handleUpdate={this.handleUpdate}
				/>
			);
			});	
		}
		
		return(				
				<div className="panel-body">
					<table className="table table-striped">
						<thead>
        					<tr className="filters">					                        
		                        <th className="hidden-xs">#</th>
		                        <th>
		                        	<input type="text" 
		                        		className="form-control" 
		                        		placeholder="Username" 
		                        		onKeyUp={this.onFilterKeyUp.bind(this)}
		                        		ref={(input) => { this.filterUsername = input; }}
		                        		disabled/>		                        	
		                        </th>
		                        <th>
		                        	<input 
		                        		type="text" 
		                        		className="form-control" 
		                        		placeholder="Fullname" 
		                        		onKeyUp={this.onFilterKeyUp.bind(this)}
		                        		ref={(input) => { this.filterFullname = input; }}
		                        		disabled/>		                        	
		                        </th>			
		                        <th>
		                        	<input 
		                        		type="text" 
		                        		className="form-control" 
		                        		placeholder="Email" 
		                        		onKeyUp={this.onFilterKeyUp.bind(this)}
		                        		ref={(input) => { this.filterEmail = input; }}
		                        		disabled/>		                        	
		                        </th>		                        					                       
		                        <th>
		                        	<input 
		                        		type="text" 
		                        		className="form-control" 
		                        		placeholder="Phone" 
		                        		onKeyUp={this.onFilterKeyUp.bind(this)}
		                        		ref={(input) => { this.filterPhone = input; }}
		                        		disabled/>		                        
		                        </th>					                        					                       
		                        <th>
		                        	<input 
		                        		type="number" 
		                        		className="form-control" 
		                        		placeholder="Grade" disabled
		                        		onKeyUp={this.onFilterKeyUp.bind(this)}
		                        		ref={(input) => { this.filterGrade = input; }}		                        		
		                        		/>		                        	
		                        </th>
		                        <th><em className="fa fa-cog"></em></th>
        					</tr> 
	                  	</thead>
	                  	<tbody>
	                  		 {rows}
	                  	</tbody>
	                  
					</table>
				</div>
		);
	}
};

class MemberPanel extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			page: null,
			defaultSize: 5,
			numPage: 1,
			filterValue: null
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
		// alert("Page - " + page);
		// this.loadMembersFromServer(page, this.state.defaultSize);
		this.filterMembersFromServer(this.state.filterValue, page);
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
		 	}		  	
		 	else {
		 		item.setAttribute('disabled', '');
		 	}
		 }
		 

		 this.forceUpdate();

		 // inputs.forEach(function(input){
		 // 	var disabled = input.getAttribute('disabled');
		 // 	console.log(disabled);
		 // });
         
	}

	changeRowOfPage(evt){
		//console.log(evt.target.value);
		

		this.filterMembersFromServer(this.state.filterValue, 0, evt.target.value);		
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
								<div classNameName="col col-xs-4">
									Page {this.state.numPage} of {pages.length}
								</div>
								<div classNameName="col col-xs-8">
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
