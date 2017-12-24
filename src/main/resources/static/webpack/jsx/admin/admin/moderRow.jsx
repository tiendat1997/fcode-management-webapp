import React from 'react'; 
import ReactDOM from 'react-dom';

class ModerRow extends React.Component{
	constructor(props){
		super(props);
	}

	render(){
		return (
			<tr>
				<td className="col-md-8">	
					<div className="row admin-label">						
						<img src={this.props.moderator.imgUrl}/>										
						<div className="info">
							<div>{this.props.moderator.fullname}</div>
							<div>Moderator</div>									
						</div>					
					</div>																		
				</td>
				<td className="col-md-4">
					<button className="btn btn-sm btn-danger">Remove</button>
				</td>				
			</tr>
		);
	}

};

export default ModerRow;