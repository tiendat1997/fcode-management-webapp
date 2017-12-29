import React from 'react'; 
import ReactDOM from 'react-dom';

import EventRow from './eventRow.jsx';


class EventTable extends React.Component{
	constructor(props){
		super(props);

	}
	componentDidMount(){
		$('#table-loading').hide();	
	}


	render(){
		if (this.props.events != null) {
			var rows = [];

			this.props.events.forEach(function(event){
				rows.push(
					<EventRow event={event}/>	
				)
			});
			
			return(
				<div className="table-responsive table-desi">					
					<table className="table table-hover" id="event-table">					
						<thead>
							<tr>							
								<th>EVENT</th>	
								<th>Start</th>
								<th>End</th>
								<th>
									<div className="text-right">
									
									</div>	
								</th>	
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
		
		return(			
			<div></div>
		);
		
	}
}

export default EventTable;
