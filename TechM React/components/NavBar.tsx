import {
  HStack,
  Image,
  InputGroup,
  Input,
  InputLeftElement,
  Button,
  IconButton,
  Flex,
  useColorMode,
} from "@chakra-ui/react";
import { BiSearch } from "react-icons/bi";
import { FaBell } from "react-icons/fa";
import DarkSwitch from "./DarkSwitch";

const NavBar = ({ onSearch }: { onSearch: (query: string) => void }) => {
  const { colorMode } = useColorMode();

  return (
    <HStack
      justifyContent="space-between"
      padding="20px"
      bg={colorMode === "light" ? "gray.100" : "gray.900"}
      color={colorMode === "light" ? "black" : "white"}
    >
      {/* Logo */}
      <Image
        src="https://media2.dev.to/dynamic/image/width=1080,height=1080,fit=cover,gravity=auto,format=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fi%2Fmltwmh8ioppzufdda6ik.png"
        boxSize="100px"
        alt="RAWG Logo"
      />

      {/* Search Bar */}
      <Flex flex="1" justify="center">
        <InputGroup width="50%">
          <InputLeftElement pointerEvents="none">
            <BiSearch color="gray.300" size="20px" />
          </InputLeftElement>
          <Input
            placeholder="Search for a game..."
            bg={colorMode === "light" ? "white" : "gray.700"}
            color={colorMode === "light" ? "black" : "white"}
            _placeholder={{ color: "gray.400" }}
            borderRadius="md"
            onChange={(e) => onSearch(e.target.value)}
          />
        </InputGroup>
      </Flex>

      {/* Buttons & Dark Mode Toggle */}
      <HStack spacing={4}>
        <Button variant="ghost" _hover={{ bg: "gray.700" }}>
          Home
        </Button>
        <Button variant="ghost" _hover={{ bg: "gray.700" }}>
          My Library
        </Button>
        <IconButton
          aria-label="Notifications"
          icon={<FaBell />}
          variant="ghost"
          _hover={{ bg: "gray.700" }}
        />
        <DarkSwitch />
      </HStack>
    </HStack>
  );
};

export default NavBar;
