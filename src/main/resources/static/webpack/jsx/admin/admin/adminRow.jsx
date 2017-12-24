import React from 'react'; 
import ReactDOM from 'react-dom';

class AdminRow extends React.Component{
	constructor(props){
		super(props);
	}

	render(){		
		if (this.props.admin != null) {
			return (
				<tr>
					<td className="col-md-8">	
						<div className="row admin-label">						
							<img src={this.props.admin.imageUrl}/>										
							<div className="info">
								<div>{this.props.admin.fullname}</div>
								<div>Admin</div>									
							</div>					
						</div>																		
					</td>
					<td className="col-md-4">
						<button className="btn btn-sm btn-danger">Remove</button>
					</td>				
				</tr>
			);			
		}
		else {
			return (
				<div></div>
			);
		}

	}

};

export default AdminRow;