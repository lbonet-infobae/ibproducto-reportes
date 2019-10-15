import React, { useState, useEffect } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/styles';
import { Card, CardContent, Grid, Typography, Avatar } from '@material-ui/core';
import BookRoundedIcon from '@material-ui/icons/BookRounded';
import { storyCountYear } from '../../../../api';

const useStyles = makeStyles(theme => ({
  content: {
    alignItems: 'center',
    display: 'flex'
  },
  title: {
    fontWeight: 700
  },
  avatar: {
    backgroundColor: theme.palette.primary.main,
    color: theme.palette.white,
    height: 56,
    width: 56
  },
  icon: {
    height: 32,
    width: 32
  }
}));

const TotalCount = props => {
  const { className, ...rest } = props;
  const classes = useStyles();
  const [currentYearCount, setCurrentYearCount] = useState(null);
  const currentYear = (new Date()).getFullYear();

  async function fetchCount() {
    const res = await storyCountYear(
      currentYear, function (res) {
        return res.data;
      }
    );

    setCurrentYearCount(res.storyCount);
  }

  useEffect(() => {
    fetchCount();
  }, []);

  return (
    <Card
      {...rest}
      className={clsx(classes.root, className)}
    >
      <CardContent>
        <Grid
          container
          justify="space-between"
        >
          <Grid item>
            <Typography
              className={classes.title}
              color="inherit"
              gutterBottom
              variant="body2"
            >
              TOTAL {currentYear}
            </Typography>
            <Typography
              color="inherit"
              variant="h3"
            >
              {currentYearCount} Artículos
            </Typography>
          </Grid>
          <Grid item>
            <Avatar className={classes.avatar}>
              <BookRoundedIcon className={classes.icon} />
            </Avatar>
          </Grid>
        </Grid>
      </CardContent>
    </Card>
  );
};

TotalCount.propTypes = {
  className: PropTypes.string
};

export default TotalCount;
