import React from 'react'; 
import ReactDOM from 'react-dom';

import ModerRow from './moderRow.jsx';

class ModerTable extends React.Component{
	constructor(props){
		super(props);
	}

	render(){
		if (this.props.moderators == null) {
			return (<div></div>);
		}


		var rows = []; 

		this.props.moderators.forEach(function(moderator){
			rows.push(
				<ModerRow moderator={moderator}/>
			)
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

};

export default ModerTable;