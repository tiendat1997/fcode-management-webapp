import React from 'react'; 
import ReactDOM from 'react-dom';

class AdminRow extends React.Component{
	constructor(props){
		super(props);
	}

	removeAdmin(){		
		var self = this; 
		var request = $.ajax({
			url: '/admin/api/member/update/admin',
			data: {
				username: self.props.admin.username,
				roleId: 2
			},
			cached: false
		}); 

		request.done(function(msg){
			if (msg === 'success'){
				// toastr.success('Remove Successfully');		
				location.reload();
			} 
			else {
				toastr.error("Remove Fail");
			}
		});

		request.fail(function(msg){
			toastr.error(msg);
		});
	}

	render(){		
		if (this.props.admin != null) {
			return (
				<tr>
					<td className="col-md-8">	
						<div className="row admin-label">						
							<img src={this.props.admin.imageUrl}/>										
							<div className="info">
								<div>{this.props.admin.fullname}</div>
								<div>Admin</div>									
							</div>					
						</div>																		
					</td>
					<td className="col-md-4">
						<button 
							className="btn btn-sm btn-danger"
							onClick={this.removeAdmin.bind(this)}>
							Remove
						</button>
					</td>				
				</tr>
			);			
		}
		else {
			return (
				<div></div>
			);
		}

	}

};

export default AdminRow;