import MaterialReactTable, { MRT_ColumnDef } from 'material-react-table';
import React, { useState, FC, useMemo, useEffect } from 'react';
import type {
  ColumnFiltersState,
  PaginationState,
  SortingState,
} from '@tanstack/react-table';
import {darken} from '@mui/material';
import {useRepositoriesStore} from "./store";
import {Repository} from "../../model/RepositoryState";

type RepositoriesApiResponse = {
  data: Array<Repository>;
  meta: {
    totalRowCount: number;
  };
};

const Repositories: FC = () => {
  const repositories = useRepositoriesStore(state => state.repositories);
  const loading = useRepositoriesStore(state => state.loading);
  const getRepositories = useRepositoriesStore(state => state.getRepositories);

  const [data, setData] = useState<Repository[]>([]);
  const [isError, setIsError] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [isRefetching, setIsRefetching] = useState(false);
  const [rowCount, setRowCount] = useState(0);

  const [columnFilters, setColumnFilters] = useState<ColumnFiltersState>([]);
  const [globalFilter, setGlobalFilter] = useState('');
  const [sorting, setSorting] = useState<SortingState>([]);
  const [pagination, setPagination] = useState<PaginationState>({
    pageIndex: 0,
    pageSize: 5,
  });

  useEffect(() => {
    const fetchData = async () => {
      if (!data.length) {
        setIsLoading(true);
      } else {
        setIsRefetching(true);
      }

      const url = new URL(
          '/api/data',
          process.env.NODE_ENV === 'production'
              ? 'https://www.material-react-table.com'
              : 'http://localhost:3000',
      );
      url.searchParams.set(
          'start',
          `${pagination.pageIndex * pagination.pageSize}`,
      );
      url.searchParams.set('size', `${pagination.pageSize}`);
      url.searchParams.set('filters', JSON.stringify(columnFilters ?? []));
      url.searchParams.set('globalFilter', globalFilter ?? '');
      url.searchParams.set('sorting', JSON.stringify(sorting ?? []));

      try {
        const response = await fetch(url.href);
        const json = (await response.json()) as RepositoriesApiResponse;
        setData(json.data);
        setRowCount(json.meta.totalRowCount);
      } catch (error) {
        setIsError(true);
        console.error(error);
        return;
      }
      setIsError(false);
      setIsLoading(false);
      setIsRefetching(false);
    };
    fetchData();

  }, [
    columnFilters,
    globalFilter,
    pagination.pageIndex,
    pagination.pageSize,
    sorting,
  ]);

  const columns = useMemo<MRT_ColumnDef<Repository>[]>(
      () => [
        {
          accessorKey: 'name',
          header: 'Name',
          size: 220
        },
        {
          accessorKey: 'language',
          header: 'Language',
          size: 120
        },
        {
          accessorKey: 'type',
          header: 'Type',
          size: 60
        },
        {
          accessorKey: 'isSynchronizationEnabled',
          header: 'Synchronization',
          size: 120
        },
        {
          accessorKey: 'synchronizedAt',
          header: 'Sync date',
          size: 120
        },
        {
          accessorKey: 'watchersCount',
          header: 'Watchers',
          size: 60
        },
        {
          accessorKey: 'star',
          header: 'Star',
          size: 60
        }
      ],
      [],
  );

  return (
      <MaterialReactTable
          columns={columns}
          data={data}
          initialState={{ showColumnFilters: false }}
          manualFiltering
          manualPagination
          manualSorting
          muiToolbarAlertBannerProps={
            isError
                ? {
                  color: 'error',
                  children: 'Error loading data',
                }
                : undefined
          }
          onColumnFiltersChange={setColumnFilters}
          onGlobalFilterChange={setGlobalFilter}
          onPaginationChange={setPagination}
          onSortingChange={setSorting}
          rowCount={rowCount}
          state={{
            columnFilters,
            globalFilter,
            isLoading,
            pagination,
            showAlertBanner: isError,
            showProgressBars: isRefetching,
            sorting,
          }}
          muiTableBodyProps={{
            sx: (theme) => ({
              '& tr:nth-of-type(odd)': {
                backgroundColor: darken(theme.palette.background.default, 0.1),
              },
            }),
          }}
          muiTablePaginationProps={{
            rowsPerPageOptions: [5, 10]
          }}
      />
  )
};

export default Repositories;
