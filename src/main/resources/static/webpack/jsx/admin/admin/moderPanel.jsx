import React from 'react'; 
import ReactDOM from 'react-dom';

import ModerTable from './moderTable.jsx';

class ModerPanel extends React.Component{
	constructor(){
		super();
		this.state = {
			moderators: null,
			suggestList: null,		
			liIndex: -1,
			addModerator: null
		}
		this.loadAllModerator = this.loadAllModerator.bind(this);
		this.resetUl = this.resetUl.bind(this);

	}
	componentDidMount() {
		this.loadAllModerator();
	}

	loadAllModerator(){
		var request = $.ajax({
			url: "/admin/api/member/get/moderator",
			method: "GET",
			cached: false
		});

		var self = this;
		request.done(function(list){
			if (list != null) {				
				self.setState({
					moderators: list
				});
			}
		}); 

		request.fail(function(msg){
			alert(msg);
		});
	}

	resetUl(){
		this.setState({
			liIndex: -1,
			addModerator: null
		});
		// Remove all selected Li in ul
		this.ulSuggest.childNodes.forEach(function(li){
			li.classList.remove('selected');
		});
	}

	addNewModerator() {
		// console.log(this.state.addAdmin);
		if (this.state.addModerator == null) {

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
				username: self.state.addModerator.username,
				roleId: 3 // roleId 3 means: MODERATOR 
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
				addModerator: this.state.suggestList[index]
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
				addModerator: this.state.suggestList[index]
			});
			
		}
	}

	removeListSuggestion(evt){
		this.setState({
			suggestList: null
		})		
	}


	getListSuggestion(evt){
		// If Enter then Submit Adding
		if (evt.keyCode == 13){			
			this.addNewModerator();
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
					<div className="col-md-4">
						Moderator 
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
										onClick={this.addNewModerator.bind(this)}>
										Add
									</button>
								</div>								
							</div>													
					</div>
				</div>
				<ModerTable moderators={this.state.moderators}/>
			</div>
		);
	}
};


ReactDOM.render(
	<ModerPanel />, document.getElementById('moderator-panel-react')
)