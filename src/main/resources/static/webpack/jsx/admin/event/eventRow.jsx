import React from 'react'; 
import ReactDOM from 'react-dom';

class EventRow extends React.Component{
	constructor(props){
		super(props);
	}


	render(){

		return(
			<tr>							
				<td className="event-main-col">
					<img src="/img/event-alt.png"/>
					<div className="info">
						<div><a href="#">{this.props.event.name}</a></div>
						<div>Innovation Hub</div>						
					</div>
				</td>
				<td>{this.props.event.dateStart}</td>
				<td>{this.props.event.dateEnd}</td>
				<td>
					<div className="text-right">
						<button className="btn btn-sm">Hide</button>
						<a className="btn btn-sm"
							href={'/admin/event/edit?eventId=' + this.props.event.eventId}>
							Edit
						</a>
						<button className="btn btn-sm">Delete</button>	
					</div>								
				</td>							
			</tr>
		);
	}
}

export default EventRow;