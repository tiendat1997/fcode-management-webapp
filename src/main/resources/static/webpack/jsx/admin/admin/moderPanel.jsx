import React from 'react'; 
import ReactDOM from 'react-dom';

import ModerTable from './moderTable.jsx';

class ModerPanel extends React.Component{
	constructor(){
		super();
		this.state = {
			moderators: null,
			suggestList: null
		}
		this.loadAllModerator = this.loadAllModerator.bind(this);

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
	getListSuggestion(evt){
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
					<li key={member.username}>
						{member.fullname}
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
					<div className="col-md-8 pull-right">
							<div className="row">
								<div className="col-md-8">
									<div className="md-form">
										<input type="search" id="admin-autocomplete"
											onKeyUp={this.getListSuggestion.bind(this)}
											className="form-control mdb-autocomplete"/>
										<ul className="mdb-autocomplete-wrap">
											{rows}											
										</ul>		
									</div>
								</div>
								
								<div className="col-md-4 pull-right">
									<button className="btn btn-primary">Add</button>
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