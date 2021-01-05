import React, { useEffect, useState } from 'react';
import {
  Stack,
  FormControl,
  Input,
  InputGroup,
  InputLeftElement,
  Center,
  Text,
  Box,
  SimpleGrid,
  Spinner,
} from '@chakra-ui/react';

import { SearchIcon } from '@chakra-ui/icons';
import GameSearchCard from './GameSearchCard';

const g = [
  {
    started: true,
    players: 5,
    maxPlayers: 10,
    rounds: 10,
    currentRound: 1,
    creator: 'Player1'
  },
  {
    started: false,
    players: 1,
    maxPlayers: 10,
    rounds: 10,
    currentRound: 0,
    creator: 'Player2'
  },
  {
    started: false,
    players: 3,
    maxPlayers: 10,
    rounds: 10,
    currentRound: 0,
    creator: 'Player3'
  }
  ,{
    started: true,
    players: 8,
    maxPlayers: 10,
    rounds: 10,
    currentRound: 10,
    creator: 'Player4'
  }];

export default function GameSearch() {
  const [search, setSearch] = useState('');
  const [loading, setLoading] = useState(false);
  const [games, setGames] = useState([]);
  const [filteredGames, setFilteredGames] = useState([]);

  const getGames = async () => {
    console.log("Gatgames")
    setLoading(true);
    // fetch games
    setGames(g);
    setFilteredGames(g);
    setLoading(false);
  };

  const updateSearch = async (event) => {
    const filtered = games.filter(game => {
      return game.creator.toLowerCase().includes(event.target.value.toLowerCase())
    })
    setSearch(event.target.value)
    setFilteredGames(filtered);
  };

  useEffect(() => {getGames()}, []);

  return (
    <Box mt='2rem'>
      <Center>
          <Stack minWidth={600} maxWidth={600} margin='1rem' spacing={5}>
            <Text fontSize='3xl'><strong>Current games</strong></Text>
            <FormControl>
              <InputGroup>
                <InputLeftElement>
                  <SearchIcon color='gray.300' />
                </InputLeftElement>
                <Input
                  id='search'
                  value={search}
                  placeholder='Search for a game'
                  onChange={updateSearch}
                />
              </InputGroup>
            </FormControl>
          </Stack>
      </Center>

      {!loading ?
          <SimpleGrid m='6' columns={3} spacing={5}>
            {filteredGames.map(game =>
                <GameSearchCard
                    started={game.started}
                    players={game.players}
                    maxPlayers={game.maxPlayers}
                    currentRound={game.currentRound}
                    rounds={game.rounds}
                    creator={game.creator}/>,
            )}
          </SimpleGrid> :
          <>
          <h1>Loading</h1>
          <Spinner
              thickness="4px"
              speed="0.65s"
              emptyColor="gray.200"
              color="blue.500"
              size="xl"
          />
          </>
      }
    </Box>
  );
}