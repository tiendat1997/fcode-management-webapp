import React from 'react';
import ReactDOM from 'react-dom';


import EventTable from './eventTable.jsx';
class EventPanel extends React.Component{
	constructor(){
		super();
	}



	render(){
		return(
			<div>
				<div className="row">
					<div className="col-sm-6">
						<div className="row">
							<div className="col-sm-12">
								<h3 className="panel-title">Upcoming Event (number)</h3>								
							</div>		
						</div>
					</div>
					<div className="col-sm-6 text-right">
						<button>Add Event</button>
					</div>				
				</div>

				<EventTable />


			</div>
		);
	}
}




ReactDOM.render(<EventPanel />, document.getElementById('event-panel-react'))