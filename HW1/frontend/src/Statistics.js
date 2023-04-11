import './App.css';
import React, { Component } from 'react'   
 
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText'; 


export default class Statistics extends Component{

    constructor(props) { 
        super(props);  
        this.state={
            
        }  

    }

    Get_Stats = async () =>{
 

        let new_data; 

        const requestOptions = {
            method: 'GET'   
        };
     
        await fetch(`http://localhost:8080/AQ/cache_statistics`, requestOptions).then(response => response.json())
        .then(data => new_data = data);
         
        this.setState({
            calls:new_data["calls"],
            total_hits: new_data["total_hits"],
            hit_percentage : new_data["hit_percentage"], 
        })
        console.log(new_data); 
 
    }  
   

    render(){  
        this.Get_Stats();

        return (
            <> 

                <p  style={{ marginTop:"20%", color:"#F4EEE0"}}> Cache Info </p>

                <p style={{fontSize:"50%"}}>Total Calls: {this.state.calls}</p>
                <p style={{fontSize:"50%"}}>Hits:  {this.state.total_hits}</p>
                <p style={{fontSize:"50%"}}>Hit Percentage: {this.state.hit_percentage} %</p>
                            
            </>
        )
    }
}