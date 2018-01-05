import React from 'react';
import ReactDOM from 'react-dom'; 

import Timeline from '../timeline.jsx';

class TimelineTree extends React.Component {
	constructor(){
		super();
		this.state = {		
			timelines: []
		}
		this.loadTimelineFromServer = this.loadTimelineFromServer.bind(this);
	}
	componentDidMount(){
		this.loadTimelineFromServer(); 
	}	

	loadTimelineFromServer(){
		var eventId = $('#eventId').text(); 

		var request = $.ajax({
			url: '/user/api/timeline/get/event',
			method: 'GET',
			data: {
				eventId: eventId
			},
			cached: false
		}); 

		var self = this;
		request.done(function(list){
			if (list != null) {
				self.setState({
					timelines: list
				});
				console.log(list);
			}
			else {
				toastr.error('Get Timeline Failure')
			}
		});

		request.fail(function(msg){
			toastr.error(msg);
		});


	}

	render(){		
		var list = []; 		
		if (this.state.timelines.length > 0) {
				this.state.timelines.forEach(function(timeline, index){			
				list.push(
					<Timeline timeline={timeline} index={index}/>
				)
			});	
		}
		else {			
			list.push(
				<li>
					<div className="timeline-panel col-md-12">
						<h4 className="col-md-12">
			                	<a>There are no Timlines</a>
			            </h4>
					</div>
				</li>
			)
		}



		return(				
			<ul className="timeline">				 
				{list}
			    <li className="clearfix no-float"></li>			
			</ul>			
		);
	}
}


ReactDOM.render(<TimelineTree />, document.getElementById('react-timeline'));