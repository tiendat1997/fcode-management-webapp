import React from 'react';
import ReactDOM from 'react-dom'; 

class Timeline extends React.Component{
	constructor(props){
		super(props);
	}
	componentDidMount(){

	}



	render(){		
		var inverted = '';
		if (this.props.index % 2 != 0) {
			inverted = 'timeline-inverted';
		}
		return(			
			  <li className={inverted}>
			        <div className="timeline-badge">
			          <a><i className="fa fa-circle" id=""></i></a>
			        </div>
			        <div className="timeline-panel">
			            <div className="row timeline-heading">
			                <h4 className="col-md-8">
			                	{this.props.timeline.name}
			                </h4>
			                <ul className="list-inline list-social-icons mb-0 col-md-4 text-right">			                	
			                	  <li className="list-inline-item">
						              <a href="#">
						                <span className="fa-stack fa-sm">
						                  <i className="fa fa-circle fa-stack-2x"></i>
						                  <i className="fa fa-facebook fa-stack-1x fa-inverse"></i>
						                </span>
						              </a>
						           </li>
						           <li className="list-inline-item">
						              <a href="#">
						                <span className="fa-stack fa-sm">
						                  <i className="fa fa-circle fa-stack-2x"></i>
						                  <i className="fa fa-google fa-stack-1x fa-inverse"></i>
						                </span>
						              </a>
						            </li>
			                </ul>
			            </div>
			            <div className="timeline-body">			                
			                <p>{this.props.timeline.description}</p>
			            </div>
			            <div className="timeline-footer">			                			                				              
		                	<h5 className="text-right">			                	
		                		{this.props.timeline.dateStart + '  -  ' + this.props.timeline.dateEnd}
		                	</h5>			                		               
			            </div>
			        </div>
			  </li>


			    
		);
	}

}

export default Timeline;