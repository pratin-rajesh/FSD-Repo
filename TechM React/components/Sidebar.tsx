import { VStack, Button, Box, Collapse, Text } from "@chakra-ui/react";
import React from "react";
import { useState } from "react";
import {
  FaGamepad,
  FaBookmark,
  FaCalendar,
  FaTrophy,
  FaList,
  FaUsers,
  FaStore,
  FaDesktop,
  FaPlaystation,
  FaXbox,
} from "react-icons/fa";

interface SidebarProps {
  onSelectCategory: (category: string) => void;
}

const Sidebar: React.FC<SidebarProps> = ({ onSelectCategory }) => {
  const [showPlatforms, setShowPlatforms] = useState(false);
  const [showGenres, setShowGenres] = useState(false);

  return (
    <Box width="250px" padding="10px" bg="black" color="white" minH="100vh">
      <VStack spacing={3} align="start">
        {/* Main Categories */}
        <Button
          leftIcon={<FaBookmark color="white" />}
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("Wishlist")}
        >
          Wishlist
        </Button>

        <Button
          leftIcon={<FaGamepad color="white" />}
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("My Library")}
        >
          My Library
        </Button>

        <Button
          leftIcon={<FaUsers color="white" />}
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("People You Follow")}
        >
          People You Follow
        </Button>

        {/* Releases Section */}
        <Text fontSize="sm" fontWeight="bold" mt={3} color="white">
          New Releases
        </Text>
        <Button
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("New Releases")}
        >
          New Releases
        </Button>
        <Button
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("Last 30 Days")}
        >
          Last 30 Days
        </Button>
        <Button
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("This Week")}
        >
          This Week
        </Button>
        <Button
          leftIcon={<FaCalendar color="white" />}
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("Release Calendar")}
        >
          Release Calendar
        </Button>

        {/* Top Games Section */}
        <Text fontSize="sm" fontWeight="bold" mt={3} color="white">
          Top Games
        </Text>
        <Button
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("Best of the Year")}
        >
          Best of the Year
        </Button>
        <Button
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("Popular in 2024")}
        >
          Popular in 2024
        </Button>
        <Button
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("All Time Top 250")}
        >
          All Time Top 250
        </Button>

        {/* Browse Section */}
        <Text fontSize="sm" fontWeight="bold" mt={3} color="white">
          Browse
        </Text>
        <Button
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("All Games")}
        >
          All Games
        </Button>
        <Button
          leftIcon={<FaList color="white" />}
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("Browse")}
        >
          Browse
        </Button>
        <Button
          leftIcon={<FaStore color="white" />}
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("Stores")}
        >
          Stores
        </Button>
        <Button
          leftIcon={<FaTrophy color="white" />}
          variant="ghost"
          color="white"
          _hover={{ bg: "gray.700" }}
          onClick={() => onSelectCategory("Collections")}
        >
          Collections
        </Button>

        {/* Platforms Section */}
        <Button
          variant="ghost"
          color="white"
          onClick={() => setShowPlatforms(!showPlatforms)}
        >
          Platforms {showPlatforms ? "▲" : "▼"}
        </Button>
        <Collapse in={showPlatforms}>
          <VStack align="start" pl={4}>
            <Button
              leftIcon={<FaDesktop color="white" />}
              variant="ghost"
              color="white"
              _hover={{ bg: "gray.700" }}
              onClick={() => onSelectCategory("PC")}
            >
              PC
            </Button>
            <Button
              leftIcon={<FaPlaystation color="white" />}
              variant="ghost"
              color="white"
              _hover={{ bg: "gray.700" }}
              onClick={() => onSelectCategory("PlayStation 4")}
            >
              PlayStation 4
            </Button>
            <Button
              leftIcon={<FaXbox color="white" />}
              variant="ghost"
              color="white"
              _hover={{ bg: "gray.700" }}
              onClick={() => onSelectCategory("Xbox One")}
            >
              Xbox One
            </Button>
            <Button
              variant="ghost"
              color="white"
              _hover={{ bg: "gray.700" }}
              onClick={() => onSelectCategory("Show All Platforms")}
            >
              Show All
            </Button>
          </VStack>
        </Collapse>

        {/* Genres Section */}
        <Button
          variant="ghost"
          color="white"
          onClick={() => setShowGenres(!showGenres)}
        >
          Genres {showGenres ? "▲" : "▼"}
        </Button>
        <Collapse in={showGenres}>
          <VStack align="start" pl={4}>
            <Button
              variant="ghost"
              color="white"
              _hover={{ bg: "gray.700" }}
              onClick={() => onSelectCategory("Free Online Games")}
            >
              Free Online Games
            </Button>
            <Button
              variant="ghost"
              color="white"
              _hover={{ bg: "gray.700" }}
              onClick={() => onSelectCategory("Action")}
            >
              Action
            </Button>
            <Button
              variant="ghost"
              color="white"
              _hover={{ bg: "gray.700" }}
              onClick={() => onSelectCategory("Strategy")}
            >
              Strategy
            </Button>
          </VStack>
        </Collapse>
      </VStack>
    </Box>
  );
};

export default Sidebar;
