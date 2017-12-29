import React from 'react'; 
import ReactDOM from 'react-dom'; 
import AwaitRow from './awaitRow.jsx';

class AwaitTable extends React.Component{
	constructor(props){
		super(props);
	}
	componentDidMount(){
		$('#table-loading').hide();	
	}

	render(){
		if (this.props.projects != null) {
			var rows = []; 
			
				this.props.projects.forEach(function(project){
					rows.push(
							<AwaitRow
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
							<div id="table-loading" className="table-full-loader">					
								<div className="load-container load6">
									<div className="loader loader-sm">Loading...</div>
								</div>						
							</div>		
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

export default AwaitTable;
