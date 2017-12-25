import React from 'react'; 
import ReactDOM from 'react-dom';

import AdminTable from './adminTable.jsx';

class AdminPanel extends React.Component{
	constructor(){
		super();
		this.state = {
			admins: null,
			suggestList: null,
			liIndex: -1,
			addAdmin: null
		}
	    this.loadAllAdmin = this.loadAllAdmin.bind(this);
	    this.resetUl = this.resetUl.bind(this);
	    this.addNewAdmin = this.addNewAdmin.bind(this);
	}
	componentDidMount(){
		this.loadAllAdmin();
	}

	loadAllAdmin(){
		var request = $.ajax({
			url: "/admin/api/member/get/admin",
			method: "GET",
			cached: false
		});

		var self = this;
		request.done(function(list){
			if (list != null) {				
				self.setState({
					admins: list
				});
			}
		}); 

		request.fail(function(msg){
			alert(msg);
		});
	}

	addNewAdmin() {
		// console.log(this.state.addAdmin);
		if (this.state.addAdmin == null) {

			// Check if search value not
			if (this.searchInput.value.length === 0){
				toastr.warning("No Member was selected");
			}					
			if (this.searchInput.value.length > 0){
				toastr.warning("Please choose the member in List");
				this.searchInput.focus();
			}


			return; 
		}
				
		var self = this;
		var request = $.ajax({
			url: '/admin/api/member/update/admin',
			data: {
				username: self.state.addAdmin.username,
				roleId: 1 // roleId 1 means: ADMIN 
			},
			cached: false
		}); 

		request.done(function(msg){
			if (msg === 'success'){
				toastr.success('Add Successfully');		
				location.reload();
			}
			else if (msg === 'enough'){
				toastr.error('Full number of admin');
			}
			else if (msg === 'existed'){
				toastr.error('Member is already an admin or an moderator');
			}
			else {
				toastr.error('Cannot Add Member');
			}
		}); 

		request.fail(function(msg){
			toastr.error(msg);
		});

		// this.state.admins.forEach(function(admin){
		// 	// console.log(admin);
		// 	if (self.state.addAdmin.username === admin.username){
		// 		existed = true;
		// 	}			
		// });

		// if (existed == true) {
		// 	toastr.error("This Member is already an admin");
		// }
		// else {
		// 	toastr.success("HIHI");
		// }
	}

	
	moveDownList(evt) {		
		if (evt.keyCode == 40) {
			// Key Down 		

			var index = this.state.liIndex + 1; 	
			var lis = this.ulSuggest.childNodes; 

			if (index > 0) {
				// Don't be the First In List				
			 	lis[index - 1].classList.remove('selected');
			}

			if (index > lis.length - 1) {
				index = 0
			}

			
			// console.log(lis[index]);
			lis[index].classList.add('selected');	

			// console.log(this.state.suggestList[index].fullname);

			this.searchInput.value = this.state.suggestList[index].fullname;			

			this.setState({
				liIndex: index,
				addAdmin: this.state.suggestList[index]
			});
		}
		else if (evt.keyCode == 38){
			// Key Up			
			
			var index = this.state.liIndex - 1; 	
			var lis = this.ulSuggest.childNodes; 

			if (index > -1){				
				lis[index + 1].classList.remove('selected');
			}
			if (index < 0){ 
				lis[0].classList.remove('selected');
				index = lis.length - 1;

			}

			lis[index].classList.add('selected');

			this.searchInput.value = this.state.suggestList[index].fullname;	
			
			this.setState({
				liIndex: index,
				addAdmin: this.state.suggestList[index]
			});
			
		}
	}

	removeListSuggestion(evt){
		this.setState({
			suggestList: null
		})		
	}


	resetUl(){
		this.setState({
			liIndex: -1,
			addAdmin: null
		});
		// Remove all selected Li in ul
		this.ulSuggest.childNodes.forEach(function(li){
			li.classList.remove('selected');
		});
	}


	getListSuggestion(evt){
		// If Enter then Submit Adding
		if (evt.keyCode == 13){			
			this.addNewAdmin();
			return;
		}
		if (evt.keyCode == 38 || evt.keyCode == 40){
			return;
		}
		// Reset liIndex to -1
        // Reset Ul
        this.resetUl();
		
		// Call AJAX
		if (evt.target.value.length == 0) {
			this.setState({
				suggestList: null
			})
			return;
		}

		var request = $.ajax({
			url : '/admin/api/member/get/10/',
			data: {
				fullname: evt.target.value
			},
			cached: false
		}); 

		var self = this; 
		request.done(function(list){
			if (list != null) {
				self.setState({
					suggestList: list
				});
				// console.log(list);
				// self.forceUpdate();
			}
			else {
				alert("Fail: " + " null List");
			}
		});

		request.fail(function(msg){
			alert("Fail " + msg);
		})

	}

	render(){		
		var rows = [];  // list li in ul 
		if (this.state.suggestList != null){
			this.state.suggestList.forEach(function(member){
				rows.push(
					<li key={member.username} className="suggestItem">
						<div className="fullname">{member.fullname}</div>
						<div className="studentCode">{member.studentCode}</div>
					</li>
				)
			});
		}
		else {
			rows = [];
		}


		return(
			<div>
				<div className="row">
					<div className="col-md-12">
						Admin 
					</div>					
					<div className="col-md-12 pull-right">
						
							<div className="row">
								<div className="col-md-8">
									<div className="md-form">
										<input 				
											ref={(input) => {this.searchInput = input}}						
											type="search" id="admin-autocomplete"
											onKeyUp={this.getListSuggestion.bind(this)}
											onFocus={this.getListSuggestion.bind(this)}
											onKeyDown={this.moveDownList.bind(this)}
											onBlur={this.removeListSuggestion.bind(this)}
											className="form-control mdb-autocomplete"/>
										<ul 
											ref={(ul) => {this.ulSuggest = ul}}
											className="mdb-autocomplete-wrap">											
											{rows}
										</ul>		
									</div>
								</div>								
								<div className="col-md-4 pull-right">
									<button 
										className="btn btn-primary"
										onClick={this.addNewAdmin}>
										Add
									</button>
								</div>								
							</div>													
					</div>
				</div>
				<AdminTable admins = {this.state.admins}/>
			</div>
		);
	}
};


ReactDOM.render(
	<AdminPanel />, document.getElementById('admin-panel-react')
)
