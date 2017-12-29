import React from 'react'; 
import ReactDOM from 'react-dom'; 
import ProjectRow from './projectRow.jsx';

class ProjectTable extends React.Component{
	constructor(props){
		super(props);
	}

	render(){
		if (this.props.projects != null) {
			var rows = []; 
			this.props.projects.forEach(function(project){
				rows.push(
						<ProjectRow 
						key={project.project.projectId} 
						project={project}/>
					)
			});

			return(
				<div className="table-responsive table-desi">					
					<table className="table table-hover" id="project-table">					
						<thead>
							<tr>							
								<th>Project</th>	
								<th>Leader</th>											
							</tr>						
						</thead>						
						<tbody>	
							{rows}							
						</tbody>		
					</table>
				</div>
			);	
		}

		// == NULL
		return (<div></div>);
		
	}
};

export default ProjectTable;
