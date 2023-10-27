import SidebarWithHeader from "./components/shared/SideBar.jsx";
import {ModalContent, Text} from "@chakra-ui/react";

const About = () => {

    return (
        <SidebarWithHeader>
            <Text fontSize={"4xl"}>About</Text>
        </SidebarWithHeader>
    )
}

export default About;