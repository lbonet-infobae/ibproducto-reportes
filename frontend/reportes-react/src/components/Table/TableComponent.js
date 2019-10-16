import React from "react";
import { useTable, useFilters, useSortBy, usePagination } from 'react-table';
import {
  Card,
  CardActions,
  CardHeader,
  CardContent,
  Button,
  Divider,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Tooltip,
  TableSortLabel
} from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';


const asd = null;
function TableAux({ columns, data }) {

  const defaultColumn = React.useMemo(
    () => ({
      // Let's set up our default Filter UI
      Filter: DefaultColumnFilter
    }),
    []
  );
  // Use the state and functions returned from useTable to build your UI
  const {
    getTableProps,
    headerGroups,
    page,
    prepareRow,
    canPreviousPage,
    canNextPage,
    pageOptions,
    pageCount,
    gotoPage,
    nextPage,
    previousPage,
    setPageSize,
    state: [{ pageIndex, pageSize }]
  } = useTable(
    {
      columns,
      data,
      defaultColumn,
      asd
    },
    useFilters,
    useSortBy,
    usePagination
  );


  // Render the UI for your table
  return (
    <>
      <Table {...getTableProps()}>
        <TableHead>
          {headerGroups.map(headerGroup => (
            <TableRow {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map(column => (
                <TableCell
                  {...column.getHeaderProps(column.getSortByToggleProps())}>
                  {column.render('Header')}
                  <span>
                    {column.isSorted
                      ? column.isSortedDesc
                        ? ' 🔽'
                        : ' 🔼'
                      : ''}
                  </span>
                  <div>{column.canFilter ? column.render('Filter') : null}</div>
                </TableCell>
              ))}
            </TableRow>
          ))}
        </TableHead>
        <TableBody>
          {}
          {page.map(
            (row, i) =>
              prepareRow(row) || (
                <TableRow {...row.getRowProps()}>
                  {row.cells.map(cell => {
                    return (
                      <TableCell {...cell.getCellProps()}>
                        {cell.render('Cell')}
                      </TableCell>
                    );
                  })}
                </TableRow>
              )
          )}
        </TableBody>
      </Table>
      <div className="pagination">
        <button onClick={() => gotoPage(0)} disabled={!canPreviousPage}>
          {'<<'}
        </button>{' '}
        <button onClick={() => previousPage()} disabled={!canPreviousPage}>
          {'<'}
        </button>{' '}
        <button onClick={() => nextPage()} disabled={!canNextPage}>
          {'>'}
        </button>{' '}
        <button onClick={() => gotoPage(pageCount - 1)} disabled={!canNextPage}>
          {'>>'}
        </button>{' '}
        <span>
          Page{' '}
          <strong>
            {pageIndex + 1} of {pageOptions.length}
          </strong>{' '}
        </span>
        <span>
          | Go to page:{' '}
          <input
            type="number"
            defaultValue={pageIndex + 1}
            onChange={e => {
              const page = e.target.value ? Number(e.target.value) - 1 : 0;
              gotoPage(page);
            }}
            style={{ width: '100px' }}
          />
        </span>{' '}
        <select
          value={pageSize}
          onChange={e => {
            setPageSize(Number(e.target.value));
          }}>
          {[10, 20, 30, 40, 50].map(pageSize => (
            <option key={pageSize} value={pageSize}>
              Show {pageSize}
            </option>
          ))}
        </select>
      </div>
    </>
  );
}





function TableComponent({ columns, data }) {
  const columnsAux = React.useMemo(() => columns, []);
  // const dataAux = React.useMemo(() => data, []);

  const useStyles = makeStyles(theme => ({
    tableContainer: {
      width: '100%'
    }
  }));

  const classes = useStyles();

  return (
    <div className={classes.tableContainer}>
      <TableAux columns={columnsAux} data={data} />
    </div>
  );
}


function DefaultColumnFilter({
  column: { filterValue, preFilteredRows, setFilter }
}) {
  const count = preFilteredRows.length;

  function killClick(e) {
    e.stopPropagation();
  }

  return (
    <input
      value={filterValue || ''}
      onClick={killClick}
      onChange={e => {
        setFilter(e.target.value || undefined); // Set undefined to remove the filter entirely
      }}
      placeholder={`Search ${count} records...`}
    />
  );
}




export default TableComponent;
