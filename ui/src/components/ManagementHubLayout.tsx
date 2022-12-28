import * as React from 'react';
import { ReactNode } from 'react';
import {
  AppBar,
  Box,
  Divider,
  Drawer,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Toolbar,
  Typography
} from '@mui/material';
import { DashboardCustomize, Storage, VerifiedUser } from '@mui/icons-material';
import { Link } from 'react-router-dom';
import { useSessionStore } from '../store';
import SettingsIcon from '@mui/icons-material/Settings';

interface Props {
  children: ReactNode;
}

const drawerWidth = 250;
const navigationItems = [
  {
    title: 'Dashboard',
    path: 'dashboard',
    icon: <DashboardCustomize />
  },
  {
    title: 'Users',
    path: 'users',
    icon: <VerifiedUser />
  },
  {
    title: 'Repositories',
    path: 'repositories',
    icon: <Storage />
  },
  {
    title: 'Organization settings',
    path: 'organizationSettings',
    icon: <SettingsIcon />
  }
];

const ManagementHubLayout = ({ children }: Props) => {
  const user = useSessionStore(state => state.user);

  return (
    <Box sx={{ display: 'flex' }}>
      <AppBar
        position="fixed"
        sx={{
          width: { sm: `calc(100% - ${drawerWidth}px)` },
          ml: { sm: `${drawerWidth}px` }
        }}
      >
        <Toolbar>
          <Typography variant="h6" noWrap component="div" sx={{ flexGrow: 1 }}>
            Management Hub
          </Typography>
          <VerifiedUser />
          <Typography variant="h6" noWrap component="div" style={{ marginLeft: 8 }}>
            {user?.login}
          </Typography>
        </Toolbar>
      </AppBar>
      <Box
        component="nav"
        sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
        aria-label="mailbox folders"
      >
        <Drawer
          variant="permanent"
          sx={{
            display: { xs: 'none', sm: 'block' },
            '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth }
          }}
          open
        >
          <Toolbar />
          <Divider />
          <List>
            {navigationItems.map((item) => (
              <ListItem key={item.title} disablePadding>
                <ListItemButton component={Link} to={item.path}>
                  <ListItemIcon>
                    {item.icon}
                  </ListItemIcon>
                  <ListItemText primary={item.title} />
                </ListItemButton>
              </ListItem>
            ))}
          </List>
        </Drawer>
      </Box>
      <Box
        component="main"
        sx={{ flexGrow: 1, p: 3, width: { sm: `calc(100% - ${drawerWidth}px)` } }}
      >
        <Toolbar />
        {children}
      </Box>
    </Box>
  );
};


export default ManagementHubLayout;
