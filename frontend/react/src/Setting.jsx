import SidebarWithHeader from "./components/shared/SideBar.jsx";
import {Input, ModalContent, Tab, TabList, TabPanel, TabPanels, Tabs, TabsProvider, Text} from "@chakra-ui/react";

const Setting = () => {

    return (
        <SidebarWithHeader>
            <Text fontSize={"4xl"}>Setting</Text>
            <Tabs variant='soft-rounded' colorScheme='green'>
                <TabList>
                    <Tab>Profile</Tab>
                    <Tab>Notification</Tab>
                    <Tab>Security</Tab>
                    <Tab>Payment</Tab>
                </TabList>
                <TabPanels>
                    <TabPanel>
                        <text>This is Profile Setting</text>
                    </TabPanel>
                    <TabPanel>
                        <text>This is Notification Setting</text>
                    </TabPanel>
                    <TabPanel>
                        <text>This is Security Setting</text>
                    </TabPanel>
                    <TabPanel>
                        <text>This is Payment Dashboard</text>
                    </TabPanel>
                </TabPanels>
            </Tabs>
        </SidebarWithHeader>
    )
}

export default Setting;