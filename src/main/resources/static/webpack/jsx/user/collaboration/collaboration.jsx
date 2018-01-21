import React from 'react';
import ReactDOM from 'react-dom';


class Collaboration extends React.Component{
	constructor(props){
		super(props);
	}

	// DELETE 
	removeCollaborator(colla){
		var sessionUsername = $('#session-username').text();
		var projectId = $('#projectId').text();

		var request = $.ajax({
			url: '/user/api/project/delete/collaborators',
			method: 'GET',
			data: {
				projectId: projectId,
				member: colla.username
			},
			cached: false
		}); 	
		var self = this;	
		request.done(function(msg){
			if (msg === 'success') {
				toastr.success("Delete Successfully");
				 
				 self.props.loadCollaborations();
				
			}
			else {
				toastr.error("Delete Fail");
			}
		});

		request.fail(function(msg){
			toastr.error(msg);
		});
	}

	render(){
		return(
			<div className="col-md-12">
				<div className="resume-item d-flex flex-column flex-md-row mb-5 bottom-border">
        			<div className="resume-content img-frame">
        				<img className="img-profile-sm"/>	
        			</div>			        			
		            <div className="resume-content mr-auto">						              
		              <h3 className="mb-0">{this.props.colla.fullName}</h3>
		              <div className="subheading mb-3">
		              	 {this.props.colla.username}
		              </div>
		              <div></div>
		             <div className="subheading mb-3">              						  
		              </div>	     	              
		            </div>
		            <div className="resume-date text-md-right">
		              <div>
		              	<a href="#" onClick={this.removeCollaborator.bind(this, this.props.colla)}><i className="fa fa-times" aria-hidden="true"></i></a>
		              </div>					             
		            </div>	            
		        </div>		
			</div>
		);
	}	

}

export default Collaboration;