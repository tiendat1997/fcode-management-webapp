import React from 'react'; 
import ReactDOM from 'react-dom'; 

class Project extends React.Component{
	constructor(props){
		super(props)
	}


	render(){
		var categories = []; 
		var members = []; 
		if (this.props.project.categories != null) {
			this.props.project.categories.forEach(function(category){
				categories.push(
					<span className="badge badge-pill pink">{category.name}</span>
					)
			});
		}

		if (this.props.project.members != null) {
			this.props.project.members.forEach(function(member){
				members.push(
					<span className="badge badge-default member-name">{member.fullname}</span>
				)				
			});				
		} else {
			members.push(
				<span className="badge badge-default member-name">Collaboration</span>
				)
		}


		var btnUpdate = [];
		if (this.props.type != null) {
			if (this.props.type == 1){
				// Owner 
				// ADD More member 
				members.push(
					<span className="add-member"><a href={'/user/project/member/add?projectId=' + this.props.project.project.projectId}><i className="fa fa-plus-square-o" aria-hidden="true"></i></a></span>
				)


				btnUpdate.push(<button className="btn btn-primary btn-sm">Edit</button>)
			}
		}
		


		return(			
		  	<div className="resume-item d-flex flex-column flex-md-row mb-5 bottom-border">
	            <div className="resume-content mr-auto">
	              <h3 className="mb-0">{this.props.project.project.name}</h3>
	              <div className="subheading mb-3">
	              	  {categories}
	              </div>
	              <div>{this.props.project.project.description}</div>
	             <div className="subheading mb-3">              	
					  {members}
	              </div>	     
	              {btnUpdate}         
	            </div>
	            <div className="resume-date text-md-right">
	              <div>
	              	<span className="text-primary">Updated on DATE ...</span>
	              </div>
	              <a href={this.props.project.project.link} target="_blank">Fork to github</a>
	            </div>	            
          	</div>		
		)
	}
}

export default Project;