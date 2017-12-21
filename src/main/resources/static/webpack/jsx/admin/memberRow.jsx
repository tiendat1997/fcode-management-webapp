import React from 'react'; 
import ReactDOM from 'react-dom'; 

export default class Member extends React.Component{
	constructor(props){
		super(props);				
		this.updateExpired = this.updateExpired.bind(this);
		this.updateRestore = this.updateRestore.bind(this);
		this.handleDelete = this.handleDelete.bind(this);
		this.handleRestore = this.handleRestore.bind(this);
	}

	componentDidMount(){
		
	}
	handleDeleteAndRestore(e){
		e.preventDefault();
		//alert(this.props.member.expired);
		if (this.props.member.expired === true){			
			this.handleRestore();					
		} else {			
			this.handleDelete();

		}
		this.forceUpdate();
	}
	updateRestore(){
		
		this.row.classList.remove("expired");		
		this.flagExpiredIcon.classList.remove("fa-recycle");
		this.flagExpiredIcon.classList.add("fa-trash");		
	}
	
	updateExpired(){		
		this.row.classList.add("expired");	
		this.flagExpiredIcon.classList.remove("fa-trash");
		this.flagExpiredIcon.classList.add("fa-recycle");	
	}	

			
	handleRestore(){
		var request = $.ajax({
			url: '/admin/api/member/restore',
			data: {
				username: this.props.member.username
			},
			method: 'GET',
			cached: false
		});				
		var self = this;
		request.done(function(msg){
			if (msg === 'success') {						
				self.updateRestore();		
				self.props.member.expired = false;	
				//toastr.success('Restore Success');					
			}
			else if (msg === 'failure') {
				toastr.error('Restore Fail');
			}
			
		}); 
		request.fail(function(msg){
			toastr.error('Restore Fail');
			// alert(msg);
		});
	}

	handleDelete(){
		// Make this member expired 		
		
		var request = $.ajax({
			url: '/admin/api/member/delete',
			data: {
				username: this.props.member.username
			},
			method: 'GET',
			cached: false
		});		
	
		var self = this;
		request.done(function(msg){
			if (msg === 'success') {				
				self.updateExpired();
				self.props.member.expired = true;	
				//toastr.success('Delete Success');
			}
			else if (msg === 'failure') {
				toastr.error('Delete Fail');
			}

			
		}); 
		request.fail(function(msg){
			toastr.error('Delete Fail');
			// alert(msg);
		});
	}
	

	render(){
		
		var isExpired = null; 
		var flagExpired = 'fa-trash';

		if (this.props.member.expired === true){			
			isExpired = 'expired'; //css
			flagExpired = 'fa-recycle'
		}
		else {			
		}

		return (
			 <tr className={isExpired}
			 	 id={this.props.member.username + 'row'}
			 	 ref={(row) => { this.row = row; }}
			 >				                           
                <td className="hidden-xs">{this.props.count}</td>
                <td>{this.props.member.username}</td>
                <td>{this.props.member.fullname}</td>
                <td>{this.props.member.email}</td>
                <td>{this.props.member.phone}</td>
                <td>{this.props.member.grade}</td>
                <td align="center">
                  <a            
                  	onClick={this.props.handleUpdate.bind(null, this.props.member)}>
                  	<i className="fa fa-pencil"></i>
                  </a>                 
                </td>
                <td>
                	<a 		                  		  				  							  	
					  	onClick={this.handleDeleteAndRestore.bind(this)}>

	                  	<i
	                  		ref={(icon) => {this.flagExpiredIcon = icon}}
	                  		className={'fa ' + flagExpired}
	                  		>
	                  	</i>
                  </a>
                </td>

              </tr>				                       	
		);
	}
};


