import React, { useState } from 'react';
import { makeStyles } from '@material-ui/styles';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import Button from '@material-ui/core/Button';

const SearchBar = props => {

  let currentYear = new Date().getFullYear();
  let currentMonth = new Date().getMonth() + 1;

  const [anio, setAnio] = useState(currentYear);
  const [mes, setMes] = useState(currentMonth);

  const classes = useStyles();

  const handleChangeAnio = event => {
    setAnio(parseInt(event.target.value));
  }


  const handleChangeMes = event => {
    setMes(parseInt(event.target.value));
  };


  return (
    <div className={classes.searchBox}>
      <Select className={classes.searchBarItem}
        value={anio}
        onChange={handleChangeAnio}
        inputProps={{
          name: 'anio',
          id: 'anio-select',
        }}
      >
        {anios.map(anio => (
          <MenuItem value={anio}>{anio}</MenuItem>
        ))}

      </Select>

      <Select
        className={classes.searchBarItem}
        value={mes}
        onChange={handleChangeMes}
        inputProps={{
          name: 'mes',
          id: 'mes-select',
        }}
      >
        {meses.map((mesM, idx) => (
          <MenuItem value={mesM.id}>{mesM.name}</MenuItem>
        ))}

      </Select>

      <Button onClick={() => props.search(anio, mes)}>
        Buscar
      </Button>
    </div>
  );

}


const anios = [2019];

const meses = [
  { id: 1, name: 'Enero' },
  { id: 2, name: 'Febrero' },
  { id: 3, name: 'Marzo' },
  { id: 4, name: 'Abril' },
  { id: 5, name: 'Mayo' },
  { id: 6, name: 'Junio' },
  { id: 7, name: 'Julio' },
  { id: 8, name: 'Agosto' },
  { id: 9, name: 'Septiembre' },
  { id: 10, name: 'Octubre' },
  { id: 11, name: 'Noviembre' },
  { id: 12, name: 'Diciembre' }
];



const useStyles = makeStyles(theme => ({
  searchBox: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'flex-start',
    margin: '1rem 0'
  },
  searchBarItem: {
    minWidth: '90px',
    marginRight: '16px'
  }
}));


export default SearchBar;
