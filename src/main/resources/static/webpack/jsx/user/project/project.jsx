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
					<span className="badge badge-default">{member.fullname}</span>
				)
			});	
		}
		


		return(			
		  	<div className="resume-item d-flex flex-column flex-md-row mb-5">
	            <div className="resume-content mr-auto">
	              <h3 className="mb-0">{this.props.project.project.name}</h3>
	              <div className="subheading mb-3">
	              	  {categories}
	              </div>
	              <div>{this.props.project.project.description}</div>
	             <div className="subheading mb-3">              	
					  {members}
	              </div>
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