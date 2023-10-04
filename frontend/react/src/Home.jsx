import SidebarWithHeader from "./components/shared/SideBar.jsx";
import {
  Grid,
  GridItem,
  IconButton,
  Text,
  Icon,
  Card,
  Tab,
  MenuGroup,
  Img,
} from "@chakra-ui/react";
import {
  AddIcon,
  HamburgerIcon,
  ExternalLinkIcon,
  RepeatIcon,
  EditIcon,
} from "@chakra-ui/icons";

import { Menu, MenuButton, MenuList, MenuItem } from "@chakra-ui/react";

const Home = () => {
  return (
    <SidebarWithHeader>
      <Menu>
        <MenuButton
          as={IconButton}
          aria-label="Option"
          icon={<HamburgerIcon />}
          variant="outline"
        />
        <MenuList>
          <MenuItem
            icon={<AddIcon />}
            command="⌘T"
            onClick={() => {
              const newTap = window.open();
              newTap.opener = null;
              newTap.location.href = "https://www.pipay.com";
            }}
          >
            New Tab
          </MenuItem>
          <MenuItem
            icon={<ExternalLinkIcon />}
            command="⌘N"
            onClick={() => {
              const newWindow = window.open();
              newWindow.opener = null;
              newWindow.location.href =
                "https://github.com/FearLessXT/spring-security";
            }}
          >
            New Window
          </MenuItem>
          <MenuItem icon={<RepeatIcon />} command="⌘⇧N">
            Open Closed Tab
          </MenuItem>
          <MenuItem
            icon={<EditIcon />}
            command="⌘O"
            onClick={() => {
              const newTab = window.open("", "_blank");
              const file = window.showOpenFilePicker().then((file) => {
                const reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = () => {
                  const fileURL = reader.result;
                  newTab.location.href = fileURL;
                };
              });
            }}
          >
            Open File...
          </MenuItem>
        </MenuList>
      </Menu>
      
    </SidebarWithHeader>
  );
};

export default Home;
