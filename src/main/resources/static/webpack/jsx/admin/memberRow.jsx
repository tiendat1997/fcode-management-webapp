import React from 'react'; 
import ReactDOM from 'react-dom'; 

export default class Member extends React.Component{
	constructor(props){
		super(props);

	}

	render(){
		var delBtn = [];
		var isExpired = null; 

		if (this.props.member.expired === true){
			delBtn.push(
				<button className="btn btn-default">
                  		<em className="fa fa-trash"></em>
                 </button>
			);

			isExpired = 'expired'; //css
		}
		else {
			delBtn.push(
				<button className="btn btn-danger" onClick={this.props.handleDelete.bind(null, this.props.member)}>
                  <em className="fa fa-trash"></em>
                </button>
			);
		}

		return (
			 <tr className={isExpired}>				                           
                <td className="hidden-xs">{this.props.count}</td>
                <td>{this.props.member.username}</td>
                <td>{this.props.member.fullname}</td>
                <td>{this.props.member.email}</td>
                <td>{this.props.member.phone}</td>
                <td>{this.props.member.grade}</td>
                <td align="center">
                  <button className="btn btn-default" onClick={this.props.handleUpdate.bind(null, this.props.member)}>
                  	<em className="fa fa-pencil"></em>
                  </button>
                  {delBtn}
                </td>
              </tr>				                       	
		);
	}
};


