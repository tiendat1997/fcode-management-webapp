import React from 'react'; 
import ReactDOM from 'react-dom';

class EventRow extends React.Component{
	constructor(props){
		super(props);
		this.deleteEvent = this.deleteEvent.bind(this);
	}

	deleteEvent(){
		var self = this;

		var request = $.ajax({
			url: '/admin/api/event/delete/event',
			data: {
				eventId: self.props.event.eventId
			},
			cached: false
		}); 

		request.done(function(msg){
			if (msg === 'success') {
				toastr.success('Delete Successfully');
				self.row.remove();
			}
			else {
				toastr.error('Delete Failure');
			}
		}); 

		request.fail(function(msg){
			toastr.error('Delete Failure');
		});


		
	}



	render(){

		return(
			<tr ref={(row) => this.row = row}>							
				<td className="event-main-col">
					<img src={this.props.event.eventCategory.imgUrl}/>
					<div className="info">
						<div><a href="#">{this.props.event.name}</a></div>
						<div>{this.props.event.eventCategory.name}</div>						
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
						<button 
							onClick={this.deleteEvent}
							className="btn btn-sm">Delete
						</button>	
					</div>								
				</td>							
			</tr>
		);
	}
}

export default EventRow;