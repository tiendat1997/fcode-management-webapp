import React from 'react'; 
import ReactDOM from 'react-dom';

import AdminRow from './adminRow.jsx';

class AdminTable extends React.Component{
	constructor(props){
		super(props);
	}

	render(){
		
		if (this.props.admins != null) {
			var rows = []; 				
			this.props.admins.forEach(function(admin){
				
				rows.push(
					<AdminRow 
						key={admin.username}
						admin={admin}
					/>
				);
			});


			return (				
				<div className="table-responsive table-desi">
					<table className="table table-hover">									
						<tbody>
							{rows}
						</tbody>
					</table>	
				</div>
				
			);
		}		 
		else {
			return (<div></div>);
		}

	}

};

export default AdminTable;