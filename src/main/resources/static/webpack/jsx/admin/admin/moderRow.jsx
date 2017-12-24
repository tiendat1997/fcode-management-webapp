import React from 'react'; 
import ReactDOM from 'react-dom';

class ModerRow extends React.Component{
	constructor(props){
		super(props);
	}

	removeModerator(){		
		var self = this; 
		var request = $.ajax({
			url: '/admin/api/member/update/admin',
			data: {
				username: self.props.moderator.username,
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
		return (
			<tr>
				<td className="col-md-8">	
					<div className="row admin-label">						
						<img src={this.props.moderator.imgUrl}/>										
						<div className="info">
							<div>{this.props.moderator.fullname}</div>
							<div>Moderator</div>									
						</div>					
					</div>																		
				</td>
				<td className="col-md-4">
					<button 
						onClick={this.removeModerator.bind(this)}
						className="btn btn-sm btn-danger">
						Remove
					</button>
				</td>				
			</tr>
		);
	}

};

export default ModerRow;