import React, { useState, useEffect } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import moment from 'moment';
import PerfectScrollbar from 'react-perfect-scrollbar';
import { makeStyles } from '@material-ui/styles';

//import { empleados as empleadosFile} from '../../../../data/empleados.js';
import TableComponent from '../../../../components/Table/TableComponent';
import { loadEmpleados } from '../../../../api';
import SearchBar from './SearchBar';

import {
  Card,
  CardActions,
  CardContent
} from '@material-ui/core';

import { getInitials } from 'helpers';

const useStyles = makeStyles(theme => ({
  root: {},
  content: {
    padding: 0
  },
  inner: {
    minWidth: 1050
  },
  nameContainer: {
    display: 'flex',
    alignItems: 'center'
  },
  avatar: {
    marginRight: theme.spacing(2)
  },
  actions: {
    justifyContent: 'flex-end'
  }
}));

const UsersTable = props => {
  const { className, users, ...rest } = props;

  const classes = useStyles();

  const [empleados, setEmpleados] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  let currentYear = new Date().getFullYear();
  let currentMonth = new Date().getMonth() + 1;


  async function fetchData() {
    const res = await searchUsers(currentYear, currentMonth);
  }

  useEffect(() => {
    fetchData();
  }, []);

  function searchUsers(anio, mes) {
    setIsLoading(true);
    loadEmpleados(anio, mes, function (res) {
      setEmpleados(res.data.usersReport);
      setIsLoading(false);
    });
  }

  return (
    <>
      <h1>Reportes por mes</h1>
      <SearchBar search={searchUsers} />
      <Card {...rest} className={clsx(classes.root, className)}>
        <CardContent className={classes.content}>
          <PerfectScrollbar>
            <div className={classes.inner}>
              {!isLoading ?
                <TableComponent columns={columns} data={empleados} /> : <p>Cargando reporte</p>
              }
            </div>
          </PerfectScrollbar>
        </CardContent>
        <CardActions className={classes.actions}></CardActions>
      </Card>
    </>
  );
};

UsersTable.propTypes = {
  className: PropTypes.string,
  users: PropTypes.array.isRequired
};

const columns = [
  {
    Header: 'Usuario',
    columns: [
      {
        Header: 'Nombre y Apellido',
        accessor: 'name',
        filter: 'fuzzyText'
      },
      {
        Header: 'UserName',
        accessor: 'username'
      }
    ]
  },
  {
    Header: 'Información',
    columns: [
      {
        Header: 'Cantidad de notas',
        accessor: 'storyCount',
        sortType: 'basic',
        filterable: true
      },
      {
        Header: 'Minutos de atraso',
        accessor: 'medianDeadlineMiss'
      }
    ]
  }
];

export default UsersTable;
