import React, { useState, useEffect } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { Bar } from 'react-chartjs-2';
import { makeStyles } from '@material-ui/styles';
import {
  Card,
  CardHeader,
  CardContent,
  CardActions,
  Divider,
  Button
} from '@material-ui/core';
import ArrowDropDownIcon from '@material-ui/icons/ArrowDropDown';
import ArrowRightIcon from '@material-ui/icons/ArrowRight';

import { storyCountMonth } from '../../../../api';
import { data, options } from './chart';

const currentYear = (new Date()).getFullYear();

const useStyles = makeStyles(() => ({
  root: {},
  chartContainer: {
    height: 400,
    position: 'relative'
  },
  actions: {
    justifyContent: 'flex-end'
  }
}));

const LatestSales = props => {
  const { className, ...rest } = props;
  const [currentYearCount, setCurrentYearCount] = useState({});
  const [loading, setLoading] = useState(false);
  const classes = useStyles();

  async function fetchNotasPorAnioYMes(anio, mes) {
    return await storyCountMonth(
      anio, mes,
      function (res) {
        return res.data;
      }
    );
  }

  useEffect(() => {
    generateData();
  }, []);

  async function generateData() {
    setLoading(true);

    //Load Days
    data.labels = [
      'Enero',
      'Febrero',
      'Marzo',
      'Abril',
      'Mayo',
      'Junio',
      'Julio',
      'Agosto',
      'Septiembre',
      'Octubre',
      'Noviembre',
      'Diciembre'
    ];

    //This year data
    data.datasets[0].data = [];
    for (let i = 1; i <= 12; i++) {
      const a = await fetchNotasPorAnioYMes(currentYear, i);
      data.datasets[0].data.push(a.storyCount);
    }

    //Last year data
    data.datasets[1].data = [];
    for (let i = 1; i <= 12; i++) {
      const a = await fetchNotasPorAnioYMes(currentYear - 1, i);
      data.datasets[1].data.push(a.storyCount);
    }

    setCurrentYearCount(data);
    setLoading(false);

  }



  return (
    <Card
      {...rest}
      className={clsx(classes.root, className)}
    >
      <CardHeader
        // action={
        //   <Button
        //     size="small"
        //     variant="text"
        //   >
        //     Last 7 days <ArrowDropDownIcon />
        //   </Button>
        // }
        title="Artículos por Mes"
      />
      <Divider />
      <CardContent>
        <div className={classes.chartContainer}>
          {loading ? <div>Cargando...</div>
            : <Bar
              data={data}
              options={options}
            />
          }
        </div>
      </CardContent>
      <Divider />
      <CardActions className={classes.actions}>
        {/* <Button
          color="primary"
          size="small"
          variant="text"
        >
          Overview <ArrowRightIcon />
        </Button> */}
      </CardActions>
    </Card>
  );
};

LatestSales.propTypes = {
  className: PropTypes.string
};

export default LatestSales;
