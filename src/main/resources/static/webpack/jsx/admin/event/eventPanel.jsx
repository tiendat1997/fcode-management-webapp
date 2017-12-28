import React from 'react';
import ReactDOM from 'react-dom';


import EventTable from './eventTable.jsx';
class EventPanel extends React.Component{
	constructor(){
		super();
		this.state = {
			events: null	
		}

		this.loadUpcomingEvent = this.loadUpcomingEvent.bind(this);
		this.loadCurrentEvent = this.loadCurrentEvent.bind(this);
	}

	componentDidMount(){
		this.loadUpcomingEvent();
	}

	loadUpcomingEvent(){
		var request = $.ajax({
			url: '/admin/api/event/get/upcoming',
			method: "GET",
			cached: false
		}); 

		var self = this; 
		request.done(function(list){
			if (list != null) {
				self.setState({
					events: list
				});
			}
		}); 

		request.fail(function(msg){
			alert(msg);
		});
	}
	loadCurrentEvent(){
		var request = $.ajax({
			url: '/admin/api/event/get/current',
			method: "GET",
			cached: false
		}); 

		var self = this; 
		request.done(function(list){
			if (list != null) {
				self.setState({
					events: list
				});
			}
		}); 

		request.fail(function(msg){
			alert(msg);
		});
	}


	changeEventOption(evt){
		if (evt.target.value == 1){
			// GET Upcoming Event
			this.loadUpcomingEvent();
		}
		else {
			// GET Current Event
			this.loadCurrentEvent();

		}
	}




	render(){
		if (this.state.events == null) {
			return (
				<div></div>
			)
		}
		
		return(
			<div>
				<div className="row">
					<div className="col-sm-6">
						<div className="row">
							<div className="col-sm-12">
								<h3 className="panel-title">Upcoming Event {this.state.events.length}</h3>								
							</div>		
						</div>
					</div>
					<div className="col-sm-6 text-right">		
						<select className="form-control" onChange={this.changeEventOption.bind(this)}>
							<option value="1">Upcoming</option>
							<option value="2">Current</option>
						</select>
						<a href="/admin/event/new" className="btn btn-primary">Add Event</a>				
					</div>				
				</div>

				<EventTable 
					events={this.state.events}
				/>

			</div>
		);
	}
}




ReactDOM.render(<EventPanel />, document.getElementById('event-panel-react'))
