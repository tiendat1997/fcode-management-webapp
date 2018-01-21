import React from 'react';
import ReactDOM from 'react-dom';

import Collaboration from './collaboration.jsx';
import Autosuggest from 'react-autosuggest';

var listSuggest = [];


// https://developer.mozilla.org/en/docs/Web/JavaScript/Guide/Regular_Expressions#Using_Special_Characters
function escapeRegexCharacters(str) {
  return str.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
}



function getSuggestions(value) {

  const escapedValue = escapeRegexCharacters(value.trim());
  
  if (escapedValue === '') {
    return [];
  }

  // Get suggestion list from server; 
  var request = $.ajax({
  	url: '/user/api/project/find/collaborators',
  	data: {
  		name: escapedValue
  	},
  	cached: false,
  	method: 'GET'
  });
  

  request.done(function(list){
  	
  	if (list != null) {  		
  		listSuggest = list;
  		console.log(list);  		
  	}
  	else {
  		toastr.error("Cannot get");
  		return [];
  	}
  });
  request.fail(function(msg){
  	 toastr.error(msg);
  	 return [];
  });



  // const regex = new RegExp('^' + escapedValue, 'i');

  // return languages.filter(function(language){
  // 	return regex.test(language.name);
  // });
  return listSuggest;
	
}


// When suggestion is clicked, Autosuggest needs to populate the input
// based on the clicked suggestion. Teach Autosuggest how to calculate the
// input value for every given suggestion.
function getSuggestionValue(suggestion){
	return suggestion.username;
}
// Use your imagination to render suggestions.
function renderSuggestion(suggestion){

	return (
		<div>
			<span>{suggestion.fullName}</span>    <span>{suggestion.studentCode}</span>			
		</div>
	);
}



class CollaPanel extends React.Component{
	constructor(props){
		super(props);
		this.state = {
	      	value: '',
	      	suggestions: [],
			listColla: []
		}
		this.loadCollaborations = this.loadCollaborations.bind(this);
		this.onChange = this.onChange.bind(this);
		this.onSuggestionsFetchRequested = this.onSuggestionsFetchRequested.bind(this);
		this.onSuggestionsClearRequested = this.onSuggestionsClearRequested.bind(this);
		this.addCollaborator = this.addCollaborator.bind(this);

	}
	componentDidMount(){
		this.loadCollaborations();
	}

    onChange(event, {newValue, method}) {
    	

	    this.setState({
	      value: newValue
	    });
  	};

  // Autosuggest will call this function every time you need to update suggestions.
  // You already implemented this logic above, so just use it.
  onSuggestionsFetchRequested({ value }){
  	const escapedValue = escapeRegexCharacters(value.trim());
  
	  if (escapedValue === '') {
	    return [];
	  }

	  // Get suggestion list from server; 
	  var request = $.ajax({
	  	url: '/user/api/project/find/collaborators/fullname',
	  	data: {
	  		name: escapedValue
	  	},
	  	cached: false,
	  	method: 'GET'
	  });
	  
	  var self = this; 

	  request.done(function(list){
	  	
	  	if (list != null) {  		
	  		listSuggest = list;
	  		console.log(list);  		
			self.setState({
		      suggestions: list
		    });
	  	}
	  	else {
	  		toastr.error("Cannot get");
	  		return [];
	  	}
	  });
	  request.fail(function(msg){
	  	 toastr.error(msg);
	  	 return [];
	  });

  	
  
  };

  // Autosuggest will call this function every time you need to clear suggestions.
  onSuggestionsClearRequested(){
    this.setState({
      suggestions: []
    });
  };

	loadCollaborations(){
		var projectId = $('#projectId').text();
		console.log(projectId);

		var request = $.ajax({

			url: '/user/api/project/get/collaborators',
			cached: false,
			data: {
				projectId: projectId
			},
			method: 'GET'
		});

		var self = this; 
		request.done(function(data){
			console.log(data);
			if (data != null) {
				self.setState({
					listColla: data
				});
			}
			else {
				toastr.error("Cannot load");
			}
		});
		request.fail(function(err){
			toastr.error(err);
		});
	}

	addCollaborator(username){
		var projectId = $('#projectId').text();
		console.log('USERNAME: ' + username);
		var sessionUsername = $('#session-username').text();

		// check if session current user 
		if (sessionUsername == username) {			
			toastr.warning("Cannot add owner of project");
			return;
		}

		// check if existed collaboration 
		var existed = false; 
		this.state.listColla.forEach(function(colla){
			if (colla.username == username) existed = true;
			
		});
		console.log(existed);
		if (existed) {
			toastr.warning("This member has already be a collaborator");

			return; 

		} else {
			var request = $.ajax({
				url: '/user/api/project/add/collaborators',
				method: 'GET',			
				data: {
					projectId: projectId,
					members: username
				},
				cached: false
			});

			var self = this; 
			request.done(function(msg){
				if (msg === 'success') {
					toastr.success("Success Adding");
					self.loadCollaborations();
				}
				else {
					toastr.error("Add Fail");
				}
			});
			request.fail(function(msg){
				toastr.error(msg);
			});

		}

		
		//this.forceUpdate();
	}	

	render(){
		const { value, suggestions } = this.state;
		 // Autosuggest will pass through all these props to the input.
	    const inputProps = {
	      id: 'username',
	      className: 'form-control',
	      placeholder: 'Type a programming language',
	      value,
	      onChange: this.onChange
	    };



		var rows = []; 
		if (this.state.listColla != null && this.state.listColla.length > 0) {
			this.state.listColla.forEach(function(colla){
				rows.push(<Collaboration colla={colla}/>)
			});
		}
		else {
			rows.push(<h2>There are no collaborators</h2>);
		}


		return(
			<div className="row">
				{/* FORM ADDING */}
				<div className="col-md-12">			
					
						<div className="md-form">
							<div className="row">
								<div className="col-md-5">
									<div id="username"></div>
									<Autosuggest										
								        suggestions={suggestions}
								        onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
								        onSuggestionsClearRequested={this.onSuggestionsClearRequested}
								        getSuggestionValue={getSuggestionValue}
								        renderSuggestion={renderSuggestion}
								        inputProps={inputProps}
								      />

								</div>
								<div className="col-md-4">
						        	<button className="btn btn-primary" onClick={this.addCollaborator.bind(this, this.state.value)}>Add collaborator</button>
						        </div>
							</div>
						</div>
					
				</div>       	
				<div className="col-md-12">
					 
					
				</div>	  				
				{/* END FORM ADD */}

				{/* LIST COLLABORATION */}
				{rows}
				{/* ./LIST COLLABORATION */}

			</div>
  		
		)
				
	}

}

ReactDOM.render(<CollaPanel/>, document.getElementById('react-panel'));
