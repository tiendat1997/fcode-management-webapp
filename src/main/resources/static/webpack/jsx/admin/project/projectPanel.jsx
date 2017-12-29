import React from 'react'; 
import ReactDOM from 'react-dom'; 
import ProjectTable from './projectTable.jsx';

class ProjectPanel extends React.Component{
	constructor(){
		super();
		this.state = {
			projects: null
		}
		this.loadWaitingProject = this.loadWaitingProject.bind(this);
	}
	componentDidMount(){
		this.loadWaitingProject(); 
	}

	loadWaitingProject(){
		var request = $.ajax({
			url: '/admin/api/project/get/notPublic',
			cached: false
		}); 

		var self = this; 
		request.done(function(list){
			if (list != null) {
				console.log(list);
				self.setState({
					projects: list
				});
			}
		}); 

		request.fail(function(msg){
			alert(msg);
		})
	}



	render(){
		return(
			<div>
				<div className="row">
					<div className="col-sm-6">
						<div className="row">
							<div className="col-sm-12">
								<h3 className="panel-title">Waiting Project (Number)</h3>								
							</div>		
						</div>
					</div>
					<div className="col-sm-6 text-right">		
						<select className="form-control" >
							<option value="1">Waiting</option>
							<option value="2">Approved</option>
						</select>						
					</div>				
				</div>
				<ProjectTable projects={this.state.projects}/>

			</div>
		);
	}
}



ReactDOM.render(<ProjectPanel />, document.getElementById('project-panel-react'));

