import React from 'react'; 
import ReactDOM from 'react-dom'; 
import Member from './memberRow.jsx';


export default class MemberTable extends React.Component{
	constructor(props){
		super(props); 

		
		// this.handleDelete.bind(this);
		// this.handleUpdate.bind(this);
	}

	handleDelete(member){
		alert("handle delete");
	}

	handleUpdate(member){
		alert("handleUpdate");
	}

	// onkeyup
	onFilterKeyUp(e){
		console.log(this.filterGrade.value);

		
			var filterValue = {
				username: this.filterUsername.value,
				fullname: this.filterFullname.value,
				email: this.filterEmail.value,
				phone: this.filterPhone.value,
				grade: this.filterGrade.value
			}	
			// grade: this.filterGrade.value
			
			this.props.filterMembersFromServer(filterValue, 0);			
		
	}

	render(){
		var rows = []; 
		if (this.props.members != null) {
			this.props.members.forEach((member, index) => {
			rows.push(
				<Member
					count={index}
					member={member}
					handleDelete={this.handleDelete}
					handleUpdate={this.handleUpdate}
				/>
			);
			});	
		}
		
		return(				
				<div className="panel-body">
					<table className="table table-striped">
						<thead>
        					<tr className="filters">					                        
		                        <th className="hidden-xs">#</th>
		                        <th>
		                        	<input type="text" 
		                        		className="form-control" 
		                        		placeholder="Username" 
		                        		onKeyUp={this.onFilterKeyUp.bind(this)}
		                        		ref={(input) => { this.filterUsername = input; }}
		                        		disabled/>		                        	
		                        </th>
		                        <th>
		                        	<input 
		                        		type="text" 
		                        		className="form-control" 
		                        		placeholder="Fullname" 
		                        		onKeyUp={this.onFilterKeyUp.bind(this)}
		                        		ref={(input) => { this.filterFullname = input; }}
		                        		disabled/>		                        	
		                        </th>			
		                        <th>
		                        	<input 
		                        		type="text" 
		                        		className="form-control" 
		                        		placeholder="Email" 
		                        		onKeyUp={this.onFilterKeyUp.bind(this)}
		                        		ref={(input) => { this.filterEmail = input; }}
		                        		disabled/>		                        	
		                        </th>		                        					                       
		                        <th>
		                        	<input 
		                        		type="text" 
		                        		className="form-control" 
		                        		placeholder="Phone" 
		                        		onKeyUp={this.onFilterKeyUp.bind(this)}
		                        		ref={(input) => { this.filterPhone = input; }}
		                        		disabled/>		                        
		                        </th>					                        					                       
		                        <th>
		                        	<input 
		                        		type="number" 
		                        		className="form-control" 
		                        		placeholder="Grade" disabled
		                        		onKeyUp={this.onFilterKeyUp.bind(this)}
		                        		ref={(input) => { this.filterGrade = input; }}		                        		
		                        		/>		                        	
		                        </th>
		                        <th><em className="fa fa-cog"></em></th>
        					</tr> 
	                  	</thead>
	                  	<tbody>
	                  		 {rows}
	                  	</tbody>
	                  
					</table>
				</div>
		);
	}
};


