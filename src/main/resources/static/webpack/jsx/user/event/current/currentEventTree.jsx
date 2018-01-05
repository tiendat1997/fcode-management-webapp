import React from 'react';
import ReactDOM from 'react-dom'; 

import Event from '../event.jsx';


class CurEventTree extends React.Component{

	constructor(){
		super();
		this.state = {
			events: []
		}
		this.loadCurrentEventFromServer = this.loadCurrentEventFromServer.bind(this); 
	}

	componentDidMount(){

	}
	loadCurrentEventFromServer(){
		var request = $.ajax({
			url: '/user/api/event/get/current',
			method: 'GET',			
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
	        		<a href="/user/event">Upcomming Event</a>
	        	</h4>
	        	<h2 className="title">Current Event</h2>        
				<h3 className="today">Past</h3>
				<ul className="timeline">
				  	{list}
				    <li className="clearfix no-float"></li>
				</ul>
			</div>
		);
	}
}


ReactDOM.render(<CurEventTree />, document.getElementById('react-current-event'));


