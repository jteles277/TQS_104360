 
import './App.css';  

import Grid from '@mui/material/Grid'; 

import Main from './Main'
import Statistics from './Statistics'





function App() { 


  return (
    <div className=" ">
      <header className="App-header" style={{justifyContent:"flex-start", backgroundColor:"#393646"}}>
        <div style={{marginTop:"5%", width:"100%"}}  >

          <Grid container spacing={3}>
            
            <Grid item xs>
              
            </Grid>

            <Grid item xs={6}>

              <Main/>

            </Grid>

            <Grid item xs>

              <Statistics/>

            </Grid>
          </Grid>
         
        </div>
         
      </header>
      
    </div>
  );
}

export default App;
