import React from 'react'; 
import ReactDOM from 'react-dom'; 
import Project from './project.jsx'; 


class ProjectPanel extends React.Component{
	constructor(){
		super(); 		
		this.state = {
			projects: [],
			type: 1 // 1 : owner - 2 : participant 
		}
		this.loadOwnProjectFromServer = this.loadOwnProjectFromServer.bind(this);
	}
	componentDidMount(){
		this.loadOwnProjectFromServer(); 
	}

	loadOwnProjectFromServer(){
		this.setState({
			type: 1
		});

		$('#btn-part').removeClass('active');
		$('#btn-own').addClass('active'); 



		var request = $.ajax({
			url: '/user/api/project/get/own',
			method: 'GET',
			cached: false
		}); 

		var self = this; 
		request.done(function(list){
			if (list != null) {
				self.setState({
					projects: list
				});
				console.log(list);
			}
			else{
				toastr.error("Failure occurs");
			} 

		});

		request.fail(function(msg){
			toastr.error(msg);
		});
	}

	loadParticipantProjectFromServer(){
		this.setState({
			type: 2
		});


		$('#btn-own').removeClass('active');
		$('#btn-part').addClass('active'); 
	


		var request = $.ajax({
			url: '/user/api/project/get/participant',
			method: 'GET',
			cached: false
		}); 

		var self = this; 
		request.done(function(list){
			if (list != null) {
				self.setState({
					projects: list
				});
				console.log(list);
			}
			else{
				toastr.error("Failure occurs");
			} 

		});

		request.fail(function(msg){
			toastr.error(msg);
		});
	}
	


	render(){		
		var list = []; 
		if (this.state.projects.length > 0) {
			var self = this;
			this.state.projects.forEach(function(project){
				list.push(
					<Project type={self.state.type} project={project}/>
				);
			});
		}
		else {
			list.push(
				<div className="resume-item d-flex flex-column flex-md-row mb-5">
					<div className="resume-content mr-auto">
	              		<h3 className="mb-0">There are no project</h3>
	              	</div>
				</div>
			);
		}


		return(
			<div>
				<div className="mb-5 row">
		          	<div className="col-md-8">
		          		<div className="col-md-12">
							<h2 className="header">Project</h2>
		          			<span className="badge badge-pill badge-success">{this.state.projects.length}</span>		          				          		 		          			
		          		</div>
		          		<div className="col-md-12">
		          			 <a href="/user/project/new" className="nav-link waves-effect waves-light" id="new-project">
		          			 	<i className="fa fa-plus fa-lg" aria-hidden="true"></i>&nbsp;New
		          			 </a>
		          		</div>
		          	</div>		          			          	
		          	<div className="col-md-4">
		          		<div className="row">
		          			<div className="col-md-12">
		          				<nav className="mb-1 navbar navbar-expand-lg navbar-dark cyan">                
				                <button			                	
				                	className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-4" 
				                		aria-controls="navbarSupportedContent-4" 
				                		aria-expanded="false" 
				                		aria-label="Toggle navigation">
				                     <span className="navbar-toggler-icon"></span>
				                </button>
				                <div className="collapse navbar-collapse" id="navbarSupportedContent-4">
				                    <ul className="navbar-nav">
				                        <li className="nav-item active" id="btn-own">
				                            <a 
				                            	onClick={this.loadOwnProjectFromServer.bind(this)}
				                            	className="nav-link waves-effect waves-light"><i className="fa fa-user-secret fa-lg" aria-hidden="true"></i>Your Own</a>
				                        </li>
				                        <li className="nav-item" id="btn-part">
				                            <a 
				                            	onClick={this.loadParticipantProjectFromServer.bind(this)}
				                            	className="nav-link waves-effect waves-light"><i className="fa fa-user-plus fa-lg" aria-hidden="true"></i>Contribute To</a>
				                        </li>  			                       

				                    </ul>
				                </div>
				            </nav>	
		          			</div>		          			
		          		</div>		          					            
		          	</div>     

		        </div>		       
		        <div className="project-details">
		        	{list}
		        </div>
	        </div>

		);
	}
}


ReactDOM.render(<ProjectPanel />, document.getElementById('react-project-panel'));
