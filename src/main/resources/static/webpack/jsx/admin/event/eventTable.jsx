import React from 'react'; 
import ReactDOM from 'react-dom';


class EventTable extends React.Component{
	constructor(props){
		super(props);
	}

	render(){
		return(
			<div className="table-responsive table-desi">
				<table className="table table-hover">					
					<tbody>
						<tr>
							<td>
								<img />
								<div className="info">
									F-Code Talk 01
								</div>
							</td>
							<td>
								<div className="row">
									<button className="btn btn-sm">Hide</button>
								<button className="btn btn-sm">Edit</button>
								<button className="btn btn-sm">Delete</button>	
								</div>								
							</td>							
						</tr>
					</tbody>		
				</table>
			</div>
		)
	}
}

export default EventTable;
