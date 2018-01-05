import React from 'react';
import ReactDOM from 'react-dom'; 

import Event from '../event.jsx';
class UpEventTree extends React.Component{
	constructor(){
		super();
		this.state = {
			events: []
		}
		this.loadEventFromServer = this.loadEventFromServer.bind(this); 
	}
	componentDidMount(){
		this.loadEventFromServer(120);
	}

	loadEventFromServer(duration){
		var request = $.ajax({
			url: '/user/api/event/get/upcomming',
			method: 'GET',
			data: {
				duration: duration
			},
			cached: false
		}); 

		var self = this; 
		request.done(function(list){
			if (list != null) {
				console.log(list);
				self.setState({
					events: list
				});
			}
			else {
				toastr.error("Load Event Failure");
			}
		}); 

		request.fail(function(){
			toastr.error('Event Get Fail')
		});
	}

	render(){
		var list = []; 
		if (this.state.events.length > 0) {
				this.state.events.forEach(function(event, index){			
				list.push(
					<Event event={event} index={index}/>
				)
			});	
		}
		else {			
			list.push(
				<li>
					<div className="timeline-panel col-md-12">
						<h4 className="col-md-12">
			                	<a>There are no event</a>
			            </h4>
					</div>
				</li>
			)
		}
		

		return(
			<div>
				<h4 className="sub-title">
	        		<a href="/user/event/current">Current Event</a>
	        	</h4>
	        	<h2 className="title">Upcomming Event</h2>        
				<div className="row">
					<div className="col-md-12 text-center">
						<button className="btn btn-sm btn-primary" onClick={this.loadEventFromServer.bind(this, 7)}>Week</button>
						<button className="btn btn-sm btn-primary" onClick={this.loadEventFromServer.bind(this, 30)}>2 Months</button>
						<button className="btn btn-sm btn-primary" onClick={this.loadEventFromServer.bind(this, 120)}>4 Months</button>					
					</div>
				</div>
				<ul className="timeline">				 
				    {list}
				    <li className="clearfix no-float"></li>
				</ul>
			</div>
			
		);
	}

}


ReactDOM.render(<UpEventTree />, document.getElementById('react-upcoming-event'));