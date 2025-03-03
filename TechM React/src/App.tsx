import { ChakraProvider, Flex, Box, useColorMode } from "@chakra-ui/react";
import NavBar from "../components/NavBar";
import Sidebar from "../components/Sidebar";
import GameList from "../components/GameList";
import { useState } from "react";
import Footer from "../components/Footer";

const App: React.FC = () => {
  const [category, setCategory] = useState<string>("Popular");
  const [page, setPage] = useState<number>(1);
  const { colorMode } = useColorMode();
  const [searchQuery, setSearchQuery] = useState<string>("");

  return (
    <ChakraProvider>
      <Box
        bg={colorMode === "light" ? "white" : "black"}
        color={colorMode === "light" ? "black" : "white"}
        minH="100vh"
      >
        <NavBar onSearch={setSearchQuery} />
        <Flex>
          <Sidebar onSelectCategory={setCategory} />
          <GameList searchQuery={searchQuery} />
        </Flex>
      </Box>
      <Footer />
    </ChakraProvider>
  );
};

export default App;
