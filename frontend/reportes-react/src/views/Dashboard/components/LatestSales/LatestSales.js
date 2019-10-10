import React, {useState, useEffect} from 'react';
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

import { storyCount } from '../../../../api';
import { data, options } from './chart';

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

  const [dataNotas, setDataNotas] = useState([]);


  const classes = useStyles();



  async function fetchData() {
    const res = await storyCount(
      { from: '2019-07-09T00:00:00', to: '2019-08-09T00:00:00' },
      function(res) {
        setDataNotas(res.data);
      }
    );
  }


    async function count(from, to) {
      let count = 0;
      await storyCount({ from, to }, function(res) {
        return res.data.storyCount;
      });
    }


  useEffect(() => {
    generateData();
    fetchData();
  }, []);


  function generateData(){
    //Load Days
    data.labels = [
      '17Aug',
      '18Aug',
      '19Aug',
      '20Aug',
      '21Aug',
      '22Aug',
      '23Aug'
    ];


    //This week
    data.datasets[0].data = [];

    //Last week
    data.datasets[1].data = [];

    console.log('7 days ', Last7Days());



    Last7Days().map(day =>{
      let dayLastMinute = new Date(day);
      dayLastMinute.setHours(23,59,59,0);
      let asd = 0;
      storyCount({ from: day, to: dayLastMinute }, function(res) {
        asd = res.data.storyCount;
      });
    });


    console.log('7 days before this week', Last7DaysOfPastWeek());
  }

  function Last7Days() {
    let result = [];
    for (let i = 0; i < 7; i++) {
      let d = new Date();
      d.setDate(d.getDate() - i);
      d.setHours(0, 0, 0, 0);
      result.push(d);
    }
    return result;
  }

  function Last7DaysOfPastWeek() {
    let result = [];
    for (let i = 0; i < 7; i++) {
      let d = new Date();
      d.setDate(d.getDate() - i - 7);
      d.setHours(0, 0, 0, 0);
      result.push(d);
    }
    return result;
  }



  return (
    <Card
      {...rest}
      className={clsx(classes.root, className)}
    >
      <CardHeader
        action={
          <Button
            size="small"
            variant="text"
          >
            Last 7 days <ArrowDropDownIcon />
          </Button>
        }
        title="Latest Sales"
      />
      <Divider />
      <CardContent>
        <div className={classes.chartContainer}>
          <Bar
            data={data}
            options={options}
          />
        </div>
      </CardContent>
      <Divider />
      <CardActions className={classes.actions}>
        <Button
          color="primary"
          size="small"
          variant="text"
        >
          Overview <ArrowRightIcon />
        </Button>
      </CardActions>
    </Card>
  );
};

LatestSales.propTypes = {
  className: PropTypes.string
};

export default LatestSales;
