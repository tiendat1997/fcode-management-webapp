

class Member extends React.Component{
	constructor(props){
		super(props);

	}

	render(){
		return (
			 <tr>				                           
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
                  <button className="btn btn-danger" onClick={this.props.handleDelete.bind(null, this.props.member)}>
                  	<em className="fa fa-trash"></em>
                  </button>
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
        					<tr>					                        
		                        <th className="hidden-xs">#</th>
		                        <th>Username</th>
		                        <th>Fullname</th>			
		                        <th>Email</th>		                        					                       
		                        <th>Phone</th>					                        					                       
		                        <th>Grade</th>
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
	constructor(){
		super();
		this.state = {
			page: null,
			defaultSize: 5

		};

		this.handlePagination = this.handlePagination.bind(this);
		this.loadMembersFromServer = this.loadMembersFromServer.bind(this);
	}


	componentDidMount(){
 		this.loadMembersFromServer(0, this.state.defaultSize);
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
		this.loadMembersFromServer(page, this.state.defaultSize);
	}

	render(){

		var pages = []; 
	
		if (this.state.page != null) {
			console.log("KHac null - ");
			
			var length = this.state.page.totalPages;			


			// from 0 
			for(var i = 0; i < length-1; i++){
				console.log("IN LOOP: " + i);
				pages.push(					
					<li>
						<a onClick={this.handlePagination.bind(this,i)}>{i + 1}</a>
					</li>					
				);
			}	
			

			return (
				<div className="panel panel-default panel-table">
						<div className="panel-heading">
							<div className="row">
								<div className="col-sm-6">
									<h3 className="panel-title">Panel Heading</h3>
								</div>
								<div className="col-sm-6 text-right">
									<button type="button"
										className="btn btn-sm btn-primary btn-create">Create
										New</button>
								</div>
							</div>
						</div>

						<MemberTable 
							members = {this.state.page.content}
						/>

						<div className="panel-footer">
							<div className="row">
								<div classNameName="col col-xs-4">Page 1 of 5</div>
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