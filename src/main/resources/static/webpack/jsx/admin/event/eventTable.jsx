import React from 'react'; 
import ReactDOM from 'react-dom';


class EventTable extends React.Component{
	constructor(props){
		super(props);
	}

	render(){
		return(
			<div className="table-responsive table-desi">
				<table className="table table-hover" id="event-table">					
					<thead>
						<tr>
							<th>DATE/TIME</th>	
							<th>EVENT</th>	
							<th>
								<div className="text-right">
									<button className="btn btn-primary">Add Event</button>
								</div>	
							</th>	
						</tr>						
					</thead>
					<tbody>
						<tr>
							<td className="event-datetime">
								<div className="col-md-12">11/11/2016</div> 
								<div className="col-md-12">13:45 -17:15</div> 																
							</td>
							<td className="event-main-col">
								<img src="/img/event-alt.png"/>
								<div className="info">
									<div><a href="#">F-Code Talk 01</a></div>
									<div>Dia Dang Coffee</div>
									
								</div>
							</td>
							<td>
								<div className="text-right">
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
