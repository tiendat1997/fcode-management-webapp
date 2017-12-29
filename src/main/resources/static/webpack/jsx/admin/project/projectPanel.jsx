import React from 'react'; 
import ReactDOM from 'react-dom'; 
import AwaitTable from './awaitTable.jsx';
import ApprovedTable from './approvedTable.jsx';

class ProjectPanel extends React.Component{
	constructor(){
		super();
		this.state = {
			projects: null,
			notPublic: true
		}
		this.loadWaitingProject = this.loadWaitingProject.bind(this);
		this.loadApprovedProject = this.loadApprovedProject.bind(this);
	}
	componentDidMount(){
		this.loadWaitingProject(1); 
	}

	loadWaitingProject(init){


		var request = $.ajax({
			url: '/admin/api/project/get/notPublic',
			cached: false
		}); 

		var self = this; 
		request.done(function(list){
			if (init == 1) {
				$('#loading').fadeOut(); 
			} 
			else {
				$('#table-loading').fadeOut();
				$('#project-table tbody tr').fadeIn();
			}

			if (list != null) {
				console.log(list);
				self.setState({
					projects: list,
					notPublic: true
				});
			}
		}); 

		request.fail(function(msg){
			alert(msg);
		})
	}

	loadApprovedProject(){
		var request = $.ajax({
			url: '/admin/api/project/get/public',
			cached: false
		}); 

		var self = this; 
		request.done(function(list){
			if (list != null) {
				console.log(list);
				self.setState({
					projects: list,
					notPublic: false
				});
			}
			$('#table-loading').fadeOut();
			$('#project-table tbody tr').fadeIn();
		}); 

		request.fail(function(msg){
			alert(msg);
		})
	}
	changeProjectOption(evt){
		if (evt.target.value == 1) {
			// GET AWAIT PROJECT
			$('#project-table tbody tr').hide();		
			$('#table-loading').fadeIn();			
			this.loadWaitingProject();	
			this.forceUpdate(); 	
		}
		else {
			// GET APPROVED PROJECT
			$('#project-table tbody tr').hide();		
			$('#table-loading').fadeIn();	
			this.loadApprovedProject();		
			this.forceUpdate();
		}
	}



	render(){
	
		if (this.state.projects != null) {
				var table = []; 
				if (this.state.notPublic == true) {
					table.push(
						<AwaitTable 
							key={'await-table'}
							projects={this.state.projects}
							notPublic={this.state.notPublic}
						/>	
					)
				}
				else {
					table.push(
						<ApprovedTable
							key={'approved-table'}
							projects={this.state.projects}
							notPublic={this.state.notPublic}
						/>	
					)
				}

			return(
				<div>
					<div className="row">
						<div className="col-sm-6">
							<div className="row">
								<div className="col-sm-12">
									<h3 className="panel-title">Waiting Project {this.state.projects.length}</h3>								
								</div>		
							</div>
						</div>
						<div className="col-sm-6 text-right">		
							<select className="form-control" onChange={this.changeProjectOption.bind(this)}>
								<option value="1">Waiting</option>
								<option value="2">Approved</option>
							</select>						
						</div>				
					</div>
					{table}			

				</div>
			);
		}

		// NULL PROJECTS 
		return (<div></div>);
	
	}
}



ReactDOM.render(<ProjectPanel />, document.getElementById('project-panel-react'));

