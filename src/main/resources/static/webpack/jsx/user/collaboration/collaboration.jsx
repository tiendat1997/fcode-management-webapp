import React from 'react';
import ReactDOM from 'react-dom';


class Collaboration extends React.Component{
	constructor(props){
		super(props);
	}
	render(){
		return(
			<div className="col-md-12">
				<div className="resume-item d-flex flex-column flex-md-row mb-5 bottom-border">
        			<div className="resume-content img-frame">
        				<img className="img-profile-sm"/>	
        			</div>			        			
		            <div className="resume-content mr-auto">						              
		              <h3 className="mb-0">{this.props.colla.fullName}</h3>
		              <div className="subheading mb-3">
		              	 {this.props.colla.username}
		              </div>
		              <div></div>
		             <div className="subheading mb-3">              						  
		              </div>	     	              
		            </div>
		            <div className="resume-date text-md-right">
		              <div>
		              	<a><i className="fa fa-times" aria-hidden="true"></i></a>
		              </div>					             
		            </div>	            
		        </div>		
			</div>
		);
	}	

}

export default Collaboration;