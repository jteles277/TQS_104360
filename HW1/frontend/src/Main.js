import './App.css';
import React, { Component } from 'react'  
import TextField from '@mui/material/TextField';  
import Box from '@mui/material/Box';
import Card from '@mui/material/Card'; 
import CardContent from '@mui/material/CardContent'; 
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button'; 
import SendIcon from '@mui/icons-material/Send';


export default class Main extends Component{

    constructor(props) { 
        super(props);  
        this.state={
            
        }  

    }

    get_Tip = async () =>{
 

        let new_data; 

        const requestOptions = {
            method: 'GET'   
        };
     
        await fetch(`http://localhost:8080/AQ/quality_by_city?region=${this.state.textFieldValue}`, requestOptions).then(response => response.json())
        .then(data => new_data = data);
         
        this.setState({
            Name:new_data["name"] + ", " + new_data["country"],
            score: new_data["score"],
            no2 : new_data["no2"],
            co : new_data["co"],
            no : new_data["no"],
            o3 : new_data["o3"],
            so2 : new_data["so2"],
            pm2_5 : new_data["pm2_5"],
            pm10 : new_data["pm10"],
            nh3 : new_data["nh3"]
            
        })
        console.log(new_data); 
 
    } 

    _handleTextFieldChange = (e) =>{ 
        this.setState({
            textFieldValue: e.target.value
        });
    }

    render(){ 

        if(this.state.Name){
            return (
                <>
                    <h3 style={{color:"#F4EEE0"}}>
                        Air Polution
                    </h3>
        
                    <TextField onChange={this._handleTextFieldChange} sx={{ input: { color: 'white' } }} color="secondary"  id="standard-basic" label="Select a Region" variant="outlined" />
                    <Button id="fetch-b" style={{marginLeft:"2%"}} variant="contained" color="secondary" endIcon={<SendIcon />}  onClick={this.get_Tip} > Fetch</Button>
                
                    
                    <Card  style={{backgroundColor:"#6D5D6E", width:"50%", marginTop:"5%"}} > 
                        <CardContent>
                            <Typography id="response-name" gutterBottom variant="h3" component="div"  style={{color:"#393646"}}>{this.state.Name}</Typography>
                            <Typography variant="body3"  style={{color:"#393646"}}>
                                Score: <span style={{color:"#F4EEE0"}}>{this.state.score} </span> 
                            </Typography>
                            <Typography variant="body1"  style={{color:"#393646", marginTop:"5%"}}>
                                no2: <span style={{color:"#F4EEE0"}}>{this.state.no2} </span> 
                            </Typography>
                            <Typography variant="body1"  style={{color:"#393646" }}>
                                co:<span style={{color:"#F4EEE0"}}> {this.state.co} </span>
                            </Typography>
                            <Typography variant="body1"  style={{color:"#393646"}}>
                                nh3: <span style={{color:"#F4EEE0"}}>{this.state.nh3} </span>
                            </Typography>
                            <Typography variant="body1"  style={{color:"#393646"}}>
                                no: <span style={{color:"#F4EEE0"}}>{this.state.no} </span>
                            </Typography>
                            <Typography variant="body1"  style={{color:"#393646"}}>
                                o3: <span style={{color:"#F4EEE0"}}>{this.state.o3}</span>
                            </Typography>
                            <Typography variant="body1"  style={{color:"#393646"}}>
                                pm10:<span style={{color:"#F4EEE0"}}>{this.state.pm10} </span> 
                            </Typography>
                            <Typography variant="body1"  style={{color:"#393646"}}>
                                pm2_5: <span style={{color:"#F4EEE0"}}>{this.state.pm2_5} </span>
                            </Typography>
                            <Typography variant="body1"  style={{color:"#393646"}}>
                                so2: <span style={{color:"#F4EEE0"}}>{this.state.so2} </span>
                            </Typography>
                        </CardContent> 
                    </Card>  
                    
                </>
            )
        }

        return (
        <>
            <h3 style={{color:"#F4EEE0"}}>
                Air Polution
            </h3>

            <TextField onChange={this._handleTextFieldChange} sx={{ input: { color: 'white' } }} color="secondary"  id="standard-basic" label="Select a Region" variant="outlined" />
            <Button style={{marginLeft:"2%"}} variant="contained" color="secondary" endIcon={<SendIcon />}  onClick={this.get_Tip}> Fetch</Button> 
              
        </>
        )
    }
}