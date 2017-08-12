import React, { Component } from 'react'
import { Link } from 'react-router'
import Header from 'grommet/components/Header'
import Box from 'grommet/components/Box'
import Heading from 'grommet/components/Heading'
import CartIcon from 'grommet/components/icons/base/Cart'

export default class AppHeader extends Component {
    render() {
        return (
            <Header
                direction='row'
                full='horizontal'
                colorIndex='accent-1'>
                <Box
                    basis='1/2'
                    pad='small'>
                    <Link
                        to='/'
                        style={{ color: '#f5f5f5' }}>
                        <Heading
                            size='small'
                            tag='h2'
                            strong
                            margin='none'>
                            <CartIcon size='medium' colorIndex='light-1' />  tokoku.jenio.com <i>kasir</i>
                        </Heading>
                    </Link>
                </Box>
                <Box
                    basis='1/2'
                    pad={{ horizontal: 'small', vertical: 'none' }}
                    direction='row'
                    justify='end'>
                    <a
                        href='http://139.59.120.226:1337/user/57eca265d4050d13616131ad/showcase'
                        style={{ color: '#f5f5f5' }}>
                        <Box pad='small'>
                            KATALOG
                        </Box>
                    </a>
                    <a
                        href='http://139.59.120.226:1337/user/57eca265d4050d13616131ad/chart'
                        style={{ color: '#f5f5f5' }}>
                        <Box pad='small'>
                            CHART
                        </Box>
                    </a>
                    <Box
                        pad='small'
                        colorIndex='neutral-1-a'>
                        KASIR
                    </Box>
                </Box>
            </Header>
        )
    }
}
