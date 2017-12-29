import React from 'react'; 
import ReactDOM from 'react-dom'; 

class ProjectRow extends React.Component{
	constructor(props){	
		super(props);
		this.state = {
			leader: null 
		}
		this.loadLeaderInfo = this.loadLeaderInfo.bind(this);
		this.getStyleLabelViaCategory = this.getStyleLabelViaCategory.bind(this);
	}
	componentDidMount(){
		this.loadLeaderInfo();
	}
	loadLeaderInfo(){
		var id = this.props.project.project.memberId; 
		

		var request = $.ajax({
			url: '/admin/api/member/get', 
			data: {
				username: id
			}
		});  
		var self = this; 
		request.done(function(leader){
			if (leader != null) {				
				self.setState({
					leader: leader
				})
			}
			else {
				alert("Cannot Get Leader");
			}
		});

		request.fail(function(msg){
			alert(msg);
		});


	}
	getStyleLabelViaCategory(id){		
		var name; 
		switch(id) {
			case 1: // Web
				name = 'pink';
				break; 
			case 2: // Mobile 
				name = 'light-blue';
				break; 	
			case 3: // Desktop 
				name = 'purple';
				break; 	
			case 4: // Embedded
				name = 'green';
				break; 	
		}

		return name; 
	}

	render(){
		if (this.props.project.categories != null) {
			var categories = []; 
			var self = this; 
			this.props.project.categories.forEach(function(category){
				var labelClass = 'badge badge-pill '; 				
				labelClass +=  self.getStyleLabelViaCategory(category.categoryId);
				categories.push(
					<span className={labelClass}>
						{category.name}
					</span>
				)
			})
			return(
				<tr>
					<td>
						<div>
							{this.props.project.project.name}
						</div>
						<div>
							{categories}
						</div>
					</td>
					<td>{this.props.project.project.memberId}</td>				
					<td>
						<div className="text-right">						
							<a  className="btn btn-sm btn-info"
								href={this.props.project.project.link}
								target="_blank">
								Review
							</a>					
							<a className="btn btn-sm btn-amber">													
								Details
							</a>
							<button 							
								className="btn btn-sm btn-danger">
								Approve
							</button>	
						</div>							
					</td>
				</tr>
			);
		}		

		// NULL 
		return(<tr></tr>);

	}
}


export default ProjectRow; 